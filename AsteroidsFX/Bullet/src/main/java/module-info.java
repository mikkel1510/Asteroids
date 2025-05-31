import dk.sdu.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.cbse.bulletsystem.BulletPlugin;
import dk.sdu.cbse.projectilesystem.ProjectileSPI;
import dk.sdu.cbse.common.Services.IEntityProcessingService;

module Bullet {
    requires Common;
    requires CommonProjectile;
    exports dk.sdu.cbse.bulletsystem;

    provides IEntityProcessingService with BulletControlSystem;
    provides ProjectileSPI with BulletPlugin;
}