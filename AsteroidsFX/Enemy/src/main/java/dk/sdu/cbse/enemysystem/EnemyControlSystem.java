package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.projectilesystem.ProjectileSPI;
import dk.sdu.cbse.commonenemy.Enemy;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    private EnemyPlugin plugin = new EnemyPlugin();


    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Enemy.class).isEmpty()){
            plugin.start(gameData, world);
        }

        double playerX = world.getPlayerX();
        double playerY = world.getPlayerY();

        for (Entity entity : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) entity;
            Random rnd = new Random();
            if (rnd.nextInt(100) % 2 == 0) {
                enemy.setRotation(enemy.getRotation() + rnd.nextInt(10) * (rnd.nextBoolean() ? 1 : -1));
            }

            double angle = Math.toDegrees(Math.atan2(playerY - enemy.getY(), playerX - enemy.getX()));
            double currentRotation = enemy.getRotation();
            double rotationDifference = angle - currentRotation;
            rotationDifference = ((rotationDifference + 180) % 360) - 180;

            if (Math.abs(rotationDifference) < 5) {
//                if (rnd.nextInt(10) == 2) {
                if (System.currentTimeMillis() - enemy.getLastFired() > enemy.getFireRate()){
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> world.addEntity(spi.createProjectile(enemy, gameData))
                    );
                    enemy.setLastFired(System.currentTimeMillis());
                }
            }

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX * 0.5);
            enemy.setY(enemy.getY() + changeY * 0.5);

            if (enemy.getX() < 0){
                enemy.setX(enemy.getX()+gameData.getDisplayWidth());
            }
            if (enemy.getX() > gameData.getDisplayWidth()){
                enemy.setX(enemy.getX() % gameData.getDisplayWidth());
            }
            if (enemy.getY() < 0){
                enemy.setY(enemy.getY()+gameData.getDisplayHeight());
            }
            if (enemy.getY() > gameData.getDisplayHeight()){
                enemy.setY(enemy.getY() % gameData.getDisplayHeight());
            }
        }
    }

    private Collection<? extends ProjectileSPI> getBulletSPIs(){
        return ServiceLoader.load(ProjectileSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
