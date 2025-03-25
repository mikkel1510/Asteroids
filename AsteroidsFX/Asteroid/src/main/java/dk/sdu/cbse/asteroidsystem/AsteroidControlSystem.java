package dk.sdu.cbse.asteroidsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IEntityProcessor;

public class AsteroidControlSystem implements IEntityProcessor {

    private AsteroidPlugin plugin = new AsteroidPlugin();
    private AsteroidSplitter splitter = new AsteroidSplitter();

    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Asteroid.class).isEmpty()){
            plugin.start(gameData, world);
        }
        for (Entity asteroid : world.getEntities(Asteroid.class)){
            if (asteroid.isHit()){
                splitter.createSplitAsteroid(asteroid, world);
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
