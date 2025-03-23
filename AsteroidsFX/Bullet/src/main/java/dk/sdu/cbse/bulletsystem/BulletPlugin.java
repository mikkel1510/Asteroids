package dk.sdu.cbse.bulletsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IGamePluginService;

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
