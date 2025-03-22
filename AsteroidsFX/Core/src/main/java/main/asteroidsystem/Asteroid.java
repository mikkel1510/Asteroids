package main.asteroidsystem;

import main.common.Entity;
import main.common.GameData;
import main.common.GameKeys;
import main.common.World;

import java.util.Random;

public class Asteroid extends Entity {
    private Entity asteroid;

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 10; i++) {
            asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData){
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(10)+5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextInt(500));
        asteroid.setY(rnd.nextInt(500));
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setColor("gray");
        return asteroid;
    };

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)){
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX()+changeX*0.1);
            asteroid.setY(asteroid.getY()+changeY*0.1);

            if (asteroid.getX() < 0){
                asteroid.setX(asteroid.getX()-gameData.getDisplayWidth());
            }
            if (asteroid.getX() > gameData.getDisplayWidth()){
                asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
            }
            if (asteroid.getY() < 0){
                asteroid.setY(asteroid.getY()-gameData.getDisplayHeight());
            }
            if (asteroid.getY() > gameData.getDisplayHeight()){
                asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
            }

        }
    }
}
