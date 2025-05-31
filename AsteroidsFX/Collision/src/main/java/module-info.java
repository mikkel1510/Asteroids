import dk.sdu.cbse.collisionsystem.CollisionDetector;
import dk.sdu.cbse.common.Services.IPostProcessingService;

module Collision {
    uses dk.sdu.common.springclient.ISpringScoreClient;
    requires Common;
    requires CommonEnemy;
    requires CommonAsteroid;
    requires CommonSpringClient;
    requires CommonProjectile;
    requires CommonPlayer;

    provides IPostProcessingService with CollisionDetector;
}