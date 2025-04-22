import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessor;

module Core {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    exports main;
    opens main to spring.core;

    uses IEntityProcessor;
    uses IGamePluginService;
    uses IPostProcessor;
}