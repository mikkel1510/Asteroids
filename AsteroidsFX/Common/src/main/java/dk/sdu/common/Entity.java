package dk.sdu.common;

import java.util.UUID;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Hello world!
 *
 */
public class Entity {
    private final UUID ID = UUID.randomUUID();


    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setPolygonCoordinates(double... polygonCoordinates) {
        this.polygonCoordinates = polygonCoordinates;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public void setColor(String color){
        this.color = Color.web(color);
    }

    private Paint color = Color.WHITE;

    public void process(GameData gameData, World world){
    }

    public void start(GameData gameData, World world){
    }

    public void stop(GameData gameData, World world){
    }

    public String getID() {
        return ID.toString();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
