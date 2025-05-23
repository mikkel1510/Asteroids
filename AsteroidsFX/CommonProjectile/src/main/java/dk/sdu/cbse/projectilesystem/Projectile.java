package dk.sdu.cbse.projectilesystem;


import dk.sdu.cbse.common.Data.Entity;

/**
 * Hello world!
 *
 */
public class Projectile extends Entity
{
    private Entity source;

    public Entity getSource() {
        return source;
    }

    public void setSource(Entity source) {
        this.source = source;
    }
}
