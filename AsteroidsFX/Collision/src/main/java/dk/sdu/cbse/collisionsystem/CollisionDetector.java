package dk.sdu.cbse.collisionsystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;
import dk.sdu.cbse.common.Services.IPostProcessor;

public class CollisionDetector implements IPostProcessor {

    @Override
    public void process(GameData gameData, World world){
        for (Entity entity1 : world.getEntities()){
            for (Entity entity2 : world.getEntities()){
                if (entity1.getID().equals(entity2.getID())){
                    continue;
                }

                if (this.collides(entity1, entity2)){
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
    }

    public boolean collides(Entity entity1, Entity entity2){
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
