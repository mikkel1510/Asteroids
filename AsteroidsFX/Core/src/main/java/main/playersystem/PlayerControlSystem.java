package main.playersystem;

import main.bulletsystem.Bullet;
import main.bulletsystem.BulletSPI;
import main.common.Data.Entity;
import main.common.Data.GameData;
import main.common.Data.GameKeys;
import main.common.Data.World;
import main.common.SPILocator;
import main.common.Services.IEntityProcessor;

public class PlayerControlSystem implements IEntityProcessor {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                for (BulletSPI bullet : SPILocator.locateAll(BulletSPI.class)){
                    world.addEntity(bullet.createBullet(player, gameData));
                }
            }
        }
    }
}
