package org.example;

import java.awt.*;

public abstract class BaseShape {
    protected int x1, y1, x2, y2;
    protected Color color;
    private static Color defaultColor = Color.BLACK;

    public BaseShape(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = defaultColor;  // Set the shape color to the default
    }

    public static void setDefaultColor(Color color) {
        defaultColor = color;
    }

    public abstract void draw(Graphics g);

    public abstract BaseShape copy();

    public void moveBy(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;
        this.x2 += dx;
        this.y2 += dy;
    }
}
