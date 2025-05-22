import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessor;
import dk.sdu.common.springclient.ISpringClient;

module Core {
    requires Common;
    requires CommonSpringClient;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires ScoringClient;
    requires CommonShip;

    exports main;
    opens main to spring.core;
    uses IEntityProcessor;
    uses IGamePluginService;
    uses IPostProcessor;
    uses ISpringClient;
}