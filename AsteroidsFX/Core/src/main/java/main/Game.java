package main;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.GameKeys;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessingService;
import dk.sdu.cbse.commonship.Spaceship;
import dk.sdu.common.springclient.ISpringScoreClient;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class Game {
    private final Pane gameWindow = new Pane();
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Map<Entity, Polygon> healthBarPolygons = new ConcurrentHashMap<>();
    private int points = 0;
    private final Text pointLabel = new Text(20, 30, "");
    private final List<IGamePluginService> pluginServiceList;
    private final List<IEntityProcessingService> entityProcessorList;
    private final List<IPostProcessingService> postProcessorList;
    private final ISpringScoreClient springScoreClient;

    public Game(List<IGamePluginService> pluginServiceList, List<IEntityProcessingService> entityProcessorList, List<IPostProcessingService> postProcessorList, ISpringScoreClient springScoreClient){
        this.pluginServiceList = pluginServiceList;
        this.entityProcessorList = entityProcessorList;
        this.postProcessorList = postProcessorList;
        this.springScoreClient = springScoreClient;
    }

    public void start(Stage stage) throws Exception {
        resetPoints();

        gameWindow.setPrefSize(gameData.getDisplayWidth(),gameData.getDisplayHeight());

        pointLabel.setFill(Color.WHITE);
        pointLabel.setFont(new Font(24));

        gameWindow.getChildren().add(pointLabel);

        Scene scene = new Scene(gameWindow);
        scene.setFill(Color.BLACK);

        for (int i = 0; i < 100; i++) {
            Rectangle rect = new Rectangle(Math.random()*(gameData.getDisplayWidth()), Math.random()*(gameData.getDisplayHeight()), 1, 1);
            rect.setFill(Color.WHITE);
            if (i % 14 == 0){
                rect.setFill(Color.YELLOW);
                rect.setHeight(2);
                rect.setWidth(2);
            }
            gameWindow.getChildren().add(rect);
        }

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)){
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

        });

        for (IGamePluginService iGamePlugin : pluginServiceList){
            iGamePlugin.start(gameData, world);
        }

        for (Entity entity : world.getEntities()){
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        render();
        stage.setScene(scene);
        stage.setTitle("Asteroids");
        stage.show();
    }

    public void render(){
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                drawEntities();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void update(){
        for (IEntityProcessingService entityProcessor : entityProcessorList){
            entityProcessor.process(gameData, world);
        }
        for (IPostProcessingService postProcessor : postProcessorList){
            postProcessor.process(gameData, world);
        }
        points = Integer.parseInt(springScoreClient.get());
        pointLabel.setText("Points: "+points);
    }

    private void drawEntities(){
        for (Entity polygonEntity : polygons.keySet()){
            if (!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
                if (polygonEntity instanceof Spaceship){
                    removedPolygon = healthBarPolygons.get(polygonEntity);
                    healthBarPolygons.remove(polygonEntity);
                    gameWindow.getChildren().remove(removedPolygon);
                }
            }
        }

        for (Entity entity : world.getEntities()){

            Polygon polygon = polygons.get(entity);
            if (polygon == null){
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            polygon.setFill(entity.getColor());

            if (entity instanceof Spaceship spaceShip){
                drawHealthBar(spaceShip);
            }
        }
    }

    private void drawHealthBar(Spaceship spaceShip){
        Polygon healthPolygon = healthBarPolygons.get(spaceShip);

        if (healthPolygon == null){
            healthPolygon = new Polygon(spaceShip.getHealthBarCoords());
            healthPolygon.setFill(Color.web(spaceShip.getHealthBarColor()));
            gameWindow.getChildren().add(healthPolygon);
            healthBarPolygons.put(spaceShip, healthPolygon);
        } else {
            healthPolygon.getPoints().setAll(
                    Arrays.stream(spaceShip.getHealthBarCoords())
                            .boxed()
                            .collect(Collectors.toList())
            );
            healthPolygon.setFill(Color.web(spaceShip.getHealthBarColor()));
        }
        healthPolygon.setTranslateX(spaceShip.getX());
        healthPolygon.setTranslateY(spaceShip.getY() + (spaceShip.getRadius() * 1.2));
    }

    void resetPoints(){
        int points = Integer.parseInt(springScoreClient.get());
        springScoreClient.post(-points);
    }
}
