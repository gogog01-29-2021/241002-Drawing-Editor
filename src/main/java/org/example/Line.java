package org.example;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends BaseShape {
    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public boolean contains(int x, int y) {
        // Implement logic to check if the point (x, y) is close to this line
        return Math.abs((y2 - y1) * (x - x1) - (x2 - x1) * (y - y1)) < 500; // Adjust sensitivity as needed
    }

    @Override
    public Line copy() {
        return new Line(x1, y1, x2, y2, color);
    }
}
