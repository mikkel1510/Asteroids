package dk.sdu.cbse.commonship;

import dk.sdu.cbse.common.Data.Entity;

public class Spaceship extends Entity {
    private int fireRate;
    private long lastFired;

    public double[] getHealthBarCoords(){
        float scale = this.getRadius();
        float barWidth = scale * 2.5f * this.getHealth()/this.getMaxHealth();
        float barHeight = scale * 0.4f;

        double leftX = -barWidth / 2;
        double rightX = barWidth / 2;
        double topY = 0;
        double bottomY = barHeight;

        return new double[]{
                leftX, topY,
                rightX, topY,
                rightX, bottomY,
                leftX, bottomY
        };
    }

    public String getHealthBarColor(){
        double healthFraction = (double) this.getHealth() / this.getMaxHealth();
        if (healthFraction < 0.5){
            return "darkred";
        } else {
            return "Green";
        }
    }

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
