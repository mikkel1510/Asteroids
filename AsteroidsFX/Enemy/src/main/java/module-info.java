import dk.sdu.cbse.bulletsystem.BulletSPI;
import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.cbse.enemysystem.EnemyPlugin;

module Enemy {
    requires Common;
    requires Player;
    requires Bullet;

    uses BulletSPI;

    provides IGamePluginService with EnemyPlugin;
    provides IEntityProcessor with EnemyControlSystem;
}