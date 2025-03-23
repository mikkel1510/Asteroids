import dk.sdu.cbse.bulletsystem.BulletSPI;
import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.playersystem.PlayerControlSystem;
import dk.sdu.cbse.playersystem.PlayerPlugin;

module Player {
    requires Common;
    requires Bullet;

    exports dk.sdu.cbse.playersystem;

    uses BulletSPI;

    provides IEntityProcessor with PlayerControlSystem;
    provides IGamePluginService with PlayerPlugin;
}