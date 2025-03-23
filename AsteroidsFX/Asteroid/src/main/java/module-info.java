import dk.sdu.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.cbse.asteroidsystem.AsteroidPlugin;
import dk.sdu.cbse.common.Services.IEntityProcessor;
import dk.sdu.cbse.common.Services.IGamePluginService;

module Asteroid {
    requires Common;

    provides IEntityProcessor with AsteroidControlSystem;
    provides IGamePluginService with AsteroidPlugin;
}