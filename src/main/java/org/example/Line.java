package org.example;

import java.awt.*;

public class Line extends BaseShape {
    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    public String getName() {
        return "Circle";
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void setEndCoordinates(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    @Override
    public boolean contains(int x, int y) {
        // Basic line containment check, can be enhanced
        return Math.abs((x2 - x1) * (y - y1) - (y2 - y1) * (x - x1)) < 1000;
    }

    @Override
    public BaseShape copy() {
        return new Line(x1, y1, x2, y2, color);
    }

    @Override
    public void moveBy(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }
}
