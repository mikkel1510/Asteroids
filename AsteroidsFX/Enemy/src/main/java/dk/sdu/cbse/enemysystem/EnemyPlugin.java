package dk.sdu.cbse.enemysystem;

import java.util.Random;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.commonenemy.Enemy;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;;


    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 1; i++) {
            enemy = createEnemy(gameData);
            world.addEntity(enemy);
        }
    }

    private Entity createEnemy(GameData gameData){
        Enemy enemy = new Enemy();
        enemy.setPolygonCoordinates(-7.5,-7.5, 15,0, -7.5,7.5);
        Random random = new Random();
        enemy.setX(random.nextInt(gameData.getDisplayHeight()));
        enemy.setY(random.nextInt(gameData.getDisplayWidth()));
        enemy.setRadius(12);
        enemy.setColor("red");
        enemy.setHealth(10);
        enemy.setMaxHealth(10);
        enemy.setFireRate(300);
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
