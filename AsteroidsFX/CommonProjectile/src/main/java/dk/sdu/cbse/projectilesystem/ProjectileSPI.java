package dk.sdu.cbse.projectilesystem;


import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.GameData;
import dk.sdu.cbse.common.Data.World;

public interface ProjectileSPI {
    Entity createProjectile(Entity shooter, GameData gameData);
}
