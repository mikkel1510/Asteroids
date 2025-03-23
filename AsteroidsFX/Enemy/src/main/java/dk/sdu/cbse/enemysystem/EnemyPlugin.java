package dk.sdu.cbse.enemysystem;

import java.util.Random;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
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
}
