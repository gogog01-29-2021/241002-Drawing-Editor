package org.example;

import java.awt.Color;
import java.awt.Graphics;

public abstract class BaseShape {
    protected int x1, y1, x2, y2;
    protected Color color;

    public BaseShape(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public abstract void draw(Graphics g);

    public void setEndCoordinates(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public boolean contains(int x, int y) {
        // For now, return false; will be overridden in subclasses with specific logic
        return false;
    }

    public void moveBy(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    public abstract BaseShape copy();
}
