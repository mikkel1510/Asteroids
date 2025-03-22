import main.bulletsystem.BulletSPI;
import main.common.Services.IEntityProcessor;
import main.common.Services.IGamePluginService;
import main.common.Services.IPostProcessor;

module Core {
    requires javafx.graphics;
    exports main;
    opens main to javafx.graphics;

    provides main.common.Services.IEntityProcessor with
            main.asteroidsystem.AsteroidControlSystem,
            main.enemysystem.EnemyControlSystem,
            main.playersystem.PlayerControlSystem,
            main.bulletsystem.BulletControlSystem;

    provides main.common.Services.IGamePluginService with
            main.asteroidsystem.AsteroidPlugin,
            main.enemysystem.EnemyPlugin,
            main.playersystem.PlayerPlugin,
            main.bulletsystem.BulletPlugin;

    provides main.common.Services.IPostProcessor with
            main.CollisionDetector;

    provides main.bulletsystem.BulletSPI with
            main.bulletsystem.BulletControlSystem;

    uses BulletSPI;
    uses IEntityProcessor;
    uses IGamePluginService;
    uses IPostProcessor;
}