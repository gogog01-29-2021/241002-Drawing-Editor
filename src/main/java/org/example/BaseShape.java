package org.example;

import java.awt.*;
import java.io.Serializable;

public abstract class BaseShape implements Serializable {
    protected int x1, y1, x2, y2;
    protected Color color;
    private static Color defaultColor = Color.BLACK;
    public abstract String getName();

    public BaseShape(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public abstract void draw(Graphics g);
    public abstract boolean contains(int x, int y);
    public abstract void setEndCoordinates(int x, int y);
    public abstract void moveBy(int dx, int dy);
    public abstract BaseShape copy();

    public static Color getDefaultColor() {
        return defaultColor;
    }

    public static void setDefaultColor(Color newColor) {
        defaultColor = newColor;
    }
    public java.awt.Rectangle getBoundingBox(){
        return new java.awt.Rectangle(
                Math.min(x1,x2),
                Math.min(y1,y2),
                Math.abs(x2-x1),
                Math.abs(y2-y1)
        );
    }
}
