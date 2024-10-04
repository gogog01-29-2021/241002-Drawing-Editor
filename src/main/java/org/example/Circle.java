package org.example;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends BaseShape {
    public Circle(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int diameter = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        g.drawOval(Math.min(x1, x2), Math.min(y1, y2), diameter, diameter);
    }

    @Override
    public boolean contains(int x, int y) {
        int radius = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1)) / 2;
        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }

    @Override
    public Circle copy() {
        return new Circle(x1, y1, x2, y2, color);
    }
}
