import dk.sdu.cbse.bulletsystem.Bullet;
import dk.sdu.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.cbse.bulletsystem.BulletPlugin;
import dk.sdu.cbse.bulletsystem.BulletSPI;
import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;

module Bullet {
    requires Common;
    exports dk.sdu.cbse.bulletsystem;

    provides IEntityProcessor with BulletControlSystem;
    provides BulletSPI with BulletPlugin;
}