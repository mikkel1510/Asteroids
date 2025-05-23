import dk.sdu.cbse.bulletsystem.Bullet;
import dk.sdu.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.cbse.bulletsystem.BulletPlugin;
import dk.sdu.cbse.bulletsystem.BulletSPI;
import dk.sdu.cbse.common.Services.IEntityProcessor;

module Bullet {
    requires Common;
    requires CommonProjectile;
    exports dk.sdu.cbse.bulletsystem;

    provides IEntityProcessor with BulletControlSystem;
    provides BulletSPI with BulletPlugin;
}