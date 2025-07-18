package dk.sdu.cbse.bulletsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.projectilesystem.ProjectileSPI;

public class BulletPlugin implements ProjectileSPI {

    @Override
    public Entity createProjectile(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setPolygonCoordinates(8, -2, 8, 2, -8, 2, -8, -2);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * (shooter.getRadius() + 2));
        bullet.setY(shooter.getY() + changeY * (shooter.getRadius() + 2));
        bullet.setRotation(shooter.getRotation());
        bullet.setColor("green");
        bullet.setRadius(1);
        bullet.setSource(shooter);
        return bullet;
    }
}
