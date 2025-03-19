package main;

import dk.sdu.bullet.Bullet;
import dk.sdu.common.Entity;
import dk.sdu.common.GameData;
import dk.sdu.common.GameKeys;
import dk.sdu.common.World;
import dk.sdu.enemysystem.Enemy;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import playersystem.Player;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Main extends Application {

    private final Pane gameWindow = new Pane();
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameWindow.setPrefSize(gameData.getDisplayWidth(),gameData.getDisplayHeight());

        Scene scene = new Scene(gameWindow);
        scene.setFill(Color.BLACK);

        for (int i = 0; i < 100; i++) {
            Rectangle rect = new Rectangle(Math.random()*(gameData.getDisplayWidth()), Math.random()*(gameData.getDisplayHeight()), 1, 1);
            rect.setFill(Color.WHITE);
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

        Player player = new Player();
        player.start(gameData, world);
        Enemy enemy = new Enemy();
        enemy.start(gameData, world);
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
        for (Entity entity : world.getEntities()){
            entity.process(gameData,world);
        }
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
            if (entity instanceof Bullet) {
                if (entity.getX() > 800 | entity.getX() < 0 | entity.getY() > 800 | entity.getY() < 0) {
                    world.getEntities().remove(entity);
                }
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            polygon.setFill(entity.getColor());
        }
    }
}
