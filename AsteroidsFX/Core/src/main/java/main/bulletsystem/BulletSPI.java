package main.bulletsystem;

import main.common.Data.Entity;
import main.common.Data.GameData;

public interface BulletSPI {
    Entity createBullet(Entity shooter, GameData gameData);
}
