package dk.sdu.cbse.asteroidsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.commonasteroid.Asteroid;
import dk.sdu.cbse.commonasteroid.IAsteroidSplitter;

import java.util.ServiceLoader;

public class AsteroidControlSystem implements IEntityProcessingService {

    private final AsteroidPlugin plugin = new AsteroidPlugin();

    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Asteroid.class).isEmpty()){
            plugin.start(gameData, world);
        }
        for (Entity asteroid : world.getEntities(Asteroid.class)){
            if (asteroid.isHit()){
                ServiceLoader.load(IAsteroidSplitter.class).findFirst().ifPresent(splitter -> splitter.createSplitAsteroid(asteroid, world));
                world.removeEntity(asteroid);
            }

            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX()+changeX*0.5);
            asteroid.setY(asteroid.getY()+changeY*0.5);

            if (asteroid.getX() < 0){
                asteroid.setX(asteroid.getX()+gameData.getDisplayWidth());
            }
            if (asteroid.getX() > gameData.getDisplayWidth()){
                asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
            }
            if (asteroid.getY() < 0){
                asteroid.setY(asteroid.getY()+gameData.getDisplayHeight());
            }
            if (asteroid.getY() > gameData.getDisplayHeight()){
                asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
            }

        }
    }
}
