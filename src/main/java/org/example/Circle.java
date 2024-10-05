package org.example;

import java.awt.*;

public class Circle extends BaseShape {

    public Circle(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public void draw(Graphics g) {
        int diameter = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        g.setColor(color);
        g.drawOval(Math.min(x1, x2), Math.min(y1, y2), diameter, diameter);
    }

    @Override
    public boolean contains(int x, int y) {
        int radius = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1)) / 2;
        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;
        return (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= radius * radius;
    }

    @Override
    public void setEndCoordinates(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    @Override
    public void moveBy(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;
        this.x2 += dx;
        this.y2 += dy;
    }

    @Override
    public BaseShape copy() {
        return new Circle(x1, y1, x2, y2, color);
    }
}
