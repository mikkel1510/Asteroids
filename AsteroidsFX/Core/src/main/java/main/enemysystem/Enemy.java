package main.enemysystem;

import main.bulletsystem.Bullet;
import main.common.Entity;
import main.common.GameData;
import main.common.World;
import main.playersystem.Player;

import java.util.Random;

public class Enemy extends Entity {
    private Entity enemy;;


    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 1; i++) {
            enemy = createEnemyShip(gameData);
            world.addEntity(enemy);
        }
    }

    private Entity createEnemyShip(GameData gameData){
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        Random random = new Random();
        enemyShip.setX(random.nextInt(gameData.getDisplayHeight()));
        enemyShip.setY(random.nextInt(gameData.getDisplayWidth()));
        enemyShip.setRadius(8);
        enemyShip.setColor("red");
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
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
                        world.addEntity(Bullet.createBullet(this, gameData));
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