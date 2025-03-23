package dk.sdu.cbse.asteroidsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    Entity asteroid;

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
    public void stop(GameData gameData, World world) {

    }
}
