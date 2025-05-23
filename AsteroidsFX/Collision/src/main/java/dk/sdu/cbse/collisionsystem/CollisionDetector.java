package dk.sdu.cbse.collisionsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IPostProcessor;
import dk.sdu.cbse.commonasteroid.Asteroid;
import dk.sdu.cbse.commonenemy.Enemy;
import dk.sdu.cbse.commonplayer.Player;
import dk.sdu.cbse.projectilesystem.Projectile;
import dk.sdu.common.springclient.ISpringClient;

import java.util.ServiceLoader;

public class CollisionDetector implements IPostProcessor {

    @Override
    public void process(GameData gameData, World world){
        for (Entity entity1 : world.getEntities()){
            for (Entity entity2 : world.getEntities()){
                if (entity1.getID().equals(entity2.getID())){
                    continue;
                }

                if (this.collides(entity1, entity2)){
                    handleCollision(entity1, entity2, world);
                    handleCollision(entity2, entity1, world);
                }
            }
        }
    }

    public void handleCollision(Entity entity, Entity hitter, World world){
        if (entity.getHealth() > 1){
            entity.setHit(true);
            entity.setHealth(entity.getHealth()-1);
        } else {
            boolean isTarget = entity instanceof Asteroid || entity instanceof Enemy; //Is the collided entity a target?
            boolean isPlayerProjectile = hitter instanceof Projectile projectile && projectile.getSource() instanceof Player; //Is the hitter sent by the player?

            if (isTarget && isPlayerProjectile){
                ServiceLoader.load(ISpringClient.class).findFirst().ifPresent(client -> client.post(1));
            }
            world.removeEntity(entity);
        }
    }

    public boolean collides(Entity entity1, Entity entity2){
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}