import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessor;

module Core {
    requires Common;
    requires javafx.graphics;
    exports main;
    uses IEntityProcessor;
    uses IGamePluginService;
    uses IPostProcessor;
}