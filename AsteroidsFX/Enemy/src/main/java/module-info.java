import dk.sdu.cbse.projectilesystem.ProjectileSPI;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.spaceship.EnemyControlSystem;
import dk.sdu.cbse.spaceship.EnemyPlugin;

module Enemy {
    requires Common;
    requires CommonEnemy;
    requires CommonPlayer;
    requires CommonProjectile;

    uses ProjectileSPI;

    provides IGamePluginService with EnemyPlugin;
    provides IEntityProcessingService with EnemyControlSystem;
}