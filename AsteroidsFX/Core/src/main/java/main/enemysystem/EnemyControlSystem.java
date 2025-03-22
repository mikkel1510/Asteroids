package main.enemysystem;

import main.bulletsystem.Bullet;
import main.bulletsystem.BulletSPI;
import main.common.Data.Entity;
import main.common.Data.GameData;
import main.common.Data.World;
import main.common.SPILocator;
import main.common.Services.IEntityProcessor;
import main.playersystem.Player;

import java.util.Random;

public class EnemyControlSystem implements IEntityProcessor {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            for (Entity enemy : world.getEntities(Enemy.class)) {
                Random rnd = new Random();
                if (rnd.nextInt(100) % 2 == 0) {
                    enemy.setRotation(enemy.getRotation() + rnd.nextInt(10) * (rnd.nextBoolean() ? 1 : -1));
                }
                ;
                double angle = Math.toDegrees(Math.atan2(player.getY() - enemy.getY(), player.getX() - enemy.getX()));
                double currentRotation = enemy.getRotation();
                double rotationDifference = angle - currentRotation;
                rotationDifference = ((rotationDifference + 180) % 360) - 180;

                if (Math.abs(rotationDifference) < 5) {
                    if (rnd.nextInt(10) == 2) {
                        for (BulletSPI bullet : SPILocator.locateAll(BulletSPI.class)){
                            world.addEntity(bullet.createBullet(enemy, gameData));
                        }
                    }
                }

                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX * 0.5);
                enemy.setY(enemy.getY() + changeY * 0.5);

            }
        }
    }
}
