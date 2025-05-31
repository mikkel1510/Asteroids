import dk.sdu.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.cbse.asteroidsystem.AsteroidPlugin;
import dk.sdu.cbse.asteroidsystem.AsteroidSplitter;
import dk.sdu.cbse.commonasteroid.IAsteroidSplitter;
import dk.sdu.cbse.common.Services.IEntityProcessingService;
import dk.sdu.cbse.common.Services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;

    uses IAsteroidSplitter;

    provides IEntityProcessingService with AsteroidControlSystem;
    provides IGamePluginService with AsteroidPlugin;
    provides IAsteroidSplitter with AsteroidSplitter;
}