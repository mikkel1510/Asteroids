package main.asteroidsystem;

import main.common.Data.Entity;
import main.common.Data.GameData;
import main.common.Data.World;
import main.common.Services.IEntityProcessor;

public class AsteroidControlSystem implements IEntityProcessor {
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
