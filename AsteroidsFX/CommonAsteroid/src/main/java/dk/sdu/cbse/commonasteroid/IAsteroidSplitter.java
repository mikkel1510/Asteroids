package dk.sdu.cbse.commonasteroid;

import dk.sdu.cbse.common.Data.Entity;
import dk.sdu.cbse.common.Data.World;

public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World world);
}
