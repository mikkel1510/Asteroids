package main;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.GameKeys;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessor;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main extends Application {

    private final Pane gameWindow = new Pane();
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Text destroyedAsteroids = new Text(20, 30, "");
    private final Text destroyedEnemies = new Text(550, 30, "");
    private List<IGamePluginService> gamePluginServices;
    private List<IEntityProcessor> entityProcessorList;
    private List<IPostProcessor> postProcessorList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Modules.class);
        gamePluginServices = context.getBean("gamePluginServiceList", List.class);
        entityProcessorList = context.getBean("entityProcessorList", List.class);
        postProcessorList = context.getBean("postProcessorList", List.class);


        gameWindow.setPrefSize(gameData.getDisplayWidth(),gameData.getDisplayHeight());

        destroyedAsteroids.setFill(Color.WHITE);
        destroyedAsteroids.setFont(new Font(24));
        destroyedEnemies.setFill(Color.WHITE);
        destroyedEnemies.setFont(new Font(24));

        gameWindow.getChildren().add(destroyedAsteroids);
        gameWindow.getChildren().add(destroyedEnemies);

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

        for (IGamePluginService iGamePlugin : gamePluginServices){
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

    private void render(){
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                draw();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void update(){
        for (IEntityProcessor entityProcessor : entityProcessorList){
            entityProcessor.process(gameData, world);
        }
        for (IPostProcessor postProcessor : postProcessorList){
            postProcessor.process(gameData, world);
        }
        destroyedAsteroids.setText("Destroyed Asteroids: "+world.getDestroyedAsteroids());
        destroyedEnemies.setText("Destroyed Enemies: "+world.getDestroyedEnemies());
    }

    private void draw(){
        for (Entity polygonEntity : polygons.keySet()){
            if (!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
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
        }
    }
}
