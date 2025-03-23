import dk.sdu.cbse.collisionsystem.CollisionDetector;
import dk.sdu.cbse.common.Services.IPostProcessor;

module Collision {
    requires Common;

    provides IPostProcessor with CollisionDetector;
}