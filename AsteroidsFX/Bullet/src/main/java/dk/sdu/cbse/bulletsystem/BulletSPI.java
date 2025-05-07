package dk.sdu.cbse.bulletsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;

public interface BulletSPI {
    Entity createBullet(Entity shooter, GameData gameData);
    void removeBullet(World world);
}
