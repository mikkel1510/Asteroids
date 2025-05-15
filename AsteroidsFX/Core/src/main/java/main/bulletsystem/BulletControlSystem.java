package main.bulletsystem;

import main.common.Data.Entity;
import main.common.Data.GameData;
import main.common.Data.World;
import main.common.Services.IEntityProcessor;

public class BulletControlSystem implements IEntityProcessor, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);

            if (bullet.getX() > 800 | bullet.getX() < 0 | bullet.getY() > 800 | bullet.getY() < 0) {
                world.getEntities().remove(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
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
