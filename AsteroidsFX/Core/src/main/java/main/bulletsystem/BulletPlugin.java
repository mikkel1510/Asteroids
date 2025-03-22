package main.bulletsystem;

import main.common.Data.Entity;
import main.common.Data.GameData;
import main.common.Data.World;
import main.common.Services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {


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
}
