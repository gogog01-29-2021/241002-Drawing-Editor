package org.example;

import java.awt.Color;

public abstract class BaseShape {
    protected int x1, y1, x2, y2;
    protected Color color;
    private static Color defaultColor = Color.BLACK;  // Static variable for default color

    public BaseShape(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;  // Set shape-specific color
    }

    // Abstract method to set end coordinates
    public abstract void setEndCoordinates(int x2, int y2);

    // Static method to set default color
    public static void setDefaultColor(Color newColor) {
        defaultColor = newColor;
    }

    // Static method to get the default color
    public static Color getDefaultColor() {
        return defaultColor;
    }

    public abstract void draw(java.awt.Graphics g);

    // Method to move the shape
    public void moveBy(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;
        this.x2 += dx;
        this.y2 += dy;
    }

    public abstract BaseShape copy();
}
