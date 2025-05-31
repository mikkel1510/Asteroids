import dk.sdu.cbse.common.Services.IPostProcessingService;
import dk.sdu.cbse.spaceship.DuplicateClass;
import dk.sdu.cbse.projectilesystem.ProjectileSPI;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.spaceship.PlayerControlSystem;
import dk.sdu.cbse.spaceship.PlayerPlugin;

module Player {
    requires Common;
    requires CommonPlayer;
    requires CommonProjectile;

    exports dk.sdu.cbse.spaceship;

    uses ProjectileSPI;

    provides IEntityProcessingService with PlayerControlSystem;
    provides IPostProcessingService with DuplicateClass;
    provides IGamePluginService with PlayerPlugin;
}