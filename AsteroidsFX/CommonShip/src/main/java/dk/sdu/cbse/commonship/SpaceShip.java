package dk.sdu.cbse.commonship;

import dk.sdu.cbse.common.Data.Entity;

public class SpaceShip extends Entity {
    private int fireRate;
    private long lastFired;


    public long getLastFired() {
        return lastFired;
    }

    public void setLastFired(long lastFired) {
        this.lastFired = lastFired;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }
}
