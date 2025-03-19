package dk.sdu.bullet;

import dk.sdu.common.Entity;
import dk.sdu.common.GameData;
import dk.sdu.common.World;
import javafx.scene.paint.Color;

/**
 * Hello world!
 *
 */
public class Bullet extends Entity
{

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities()){
            if (entity.getClass() == Bullet.class){
                world.removeEntity(entity);
            }
        }
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);
        }
    }

    public static Entity createBullet(Entity shooter, GameData gameData){
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());
        bullet.setColor("green");
        bullet.setRadius(1);
        return bullet;
    }
}
