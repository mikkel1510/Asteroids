import dk.sdu.cbse.collisionsystem.CollisionDetector;
import dk.sdu.cbse.common.Services.IPostProcessor;

module Collision {
    uses dk.sdu.common.springclient.ISpringClient;
    requires Common;
    requires CommonEnemy;
    requires CommonAsteroid;
    requires CommonSpringClient;

    provides IPostProcessor with CollisionDetector;
}