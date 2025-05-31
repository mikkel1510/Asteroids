import dk.sdu.cbse.projectilesystem.ProjectileSPI;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.playersystem.PlayerControlSystem;
import dk.sdu.cbse.playersystem.PlayerPlugin;

module Player {
    requires Common;
    requires CommonPlayer;
    requires CommonProjectile;

    exports dk.sdu.cbse.playersystem;

    uses ProjectileSPI;

    provides IEntityProcessingService with PlayerControlSystem;
    provides IGamePluginService with PlayerPlugin;
}