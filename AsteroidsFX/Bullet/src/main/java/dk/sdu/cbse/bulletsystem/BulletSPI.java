package dk.sdu.cbse.bulletsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;

public interface BulletSPI {
    Entity createBullet(Entity shooter, GameData gameData);
}
