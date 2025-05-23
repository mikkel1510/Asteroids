import dk.sdu.cbse.collisionsystem.CollisionDetector;
import dk.sdu.cbse.common.Services.IPostProcessor;

module Collision {
    uses dk.sdu.common.springclient.ISpringClient;
    requires Common;
    requires CommonEnemy;
    requires CommonAsteroid;
    requires CommonSpringClient;
    requires CommonProjectile;
    requires CommonPlayer;

    provides IPostProcessor with CollisionDetector;
}