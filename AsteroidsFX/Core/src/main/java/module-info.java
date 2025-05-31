import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;
import dk.sdu.cbse.common.Services.IPostProcessingService;
import dk.sdu.common.springclient.ISpringScoreClient;

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
    uses IEntityProcessingService;
    uses IGamePluginService;
    uses IPostProcessingService;
    uses ISpringScoreClient;
}