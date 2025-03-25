package dk.sdu.cbse.common.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private int destroyedAsteroids;
    private int destroyedEnemies;
    private double playerX;
    private double playerY;

    public double getPlayerY() {
        return playerY;
    }

    public void setPlayerY(double playerY) {
        this.playerY = playerY;
    }


    public double getPlayerX() {
        return playerX;
    }

    public void setPlayerX(double playerX) {
        this.playerX = playerX;
    }


    public int getDestroyedAsteroids() {
        return destroyedAsteroids;
    }


    public int getDestroyedEnemies() {
        return destroyedEnemies;
    }

    public <T> void updateDestroyedEntities(Class<T> type) { //TODO: Not good, tight coupling
        String entityName = type.getSimpleName();
        if (entityName.equals("Asteroid")){
            destroyedAsteroids += 1;
        } else if (entityName.equals("Enemy")){
            destroyedEnemies += 1;
        }
    }

    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }


    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }
}
