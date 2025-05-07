package dk.sdu.cbse.bulletsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IEntityProcessor;

public class BulletControlSystem implements IEntityProcessor {
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
}
