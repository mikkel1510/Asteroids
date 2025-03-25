package dk.sdu.cbse.asteroidsystem;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.World;

import java.util.Random;

public class AsteroidSplitter implements IAsteroidSplitter{

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        if (e.getRadius() > 5){
            Entity asteroid1 = new Asteroid();
            Entity asteroid2 = new Asteroid();
            float size = (float) (e.getRadius()/1.5);
            asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
            asteroid2.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);

            Random rnd = new Random();
            asteroid1.setX(e.getX()+rnd.nextInt(50));
            asteroid1.setY(e.getY()+rnd.nextInt(50));
            asteroid2.setX(e.getX()-rnd.nextInt(50));
            asteroid2.setY(e.getY()-rnd.nextInt(50));

            asteroid1.setRotation(e.getRotation()+5);
            asteroid2.setRotation(e.getRotation()-5);

            asteroid1.setRadius(size);
            asteroid2.setRadius(size);

            asteroid1.setColor("grey");
            asteroid2.setColor("grey");

            asteroid1.setHealth(e.getHealth());
            asteroid2.setHealth(e.getHealth());
            world.addEntity(asteroid1);
            world.addEntity(asteroid2);
        }
    }

}
