package dk.sdu.cbse.collisionsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IPostProcessor;
import dk.sdu.cbse.commonasteroid.Asteroid;
import dk.sdu.cbse.commonenemy.Enemy;
import dk.sdu.common.springclient.ISpringClient;

import java.util.ServiceLoader;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CollisionDetector implements IPostProcessor {

    @Override
    public void process(GameData gameData, World world){
        for (Entity entity1 : world.getEntities()){
            for (Entity entity2 : world.getEntities()){
                if (entity1.getID().equals(entity2.getID())){
                    continue;
                }

                if (this.collides(entity1, entity2)){
                    handleCollision(entity1, world);
                    handleCollision(entity2, world);
                }
            }
        }
    }

    public void handleCollision(Entity entity, World world){
        if (entity instanceof Enemy || entity instanceof Asteroid){
            ServiceLoader.load(ISpringClient.class).findFirst().ifPresent(client -> client.post(1));
        }
        if (entity.getHealth() > 1){
            entity.setHit(true);
            entity.setHealth(entity.getHealth()-1);
        } else {
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