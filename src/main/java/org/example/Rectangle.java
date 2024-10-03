package org.example;

import java.awt.*;

public class Rectangle extends BaseShape {
    public Rectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

    @Override
    public BaseShape copy() {
        return new Rectangle(x1, y1, x2, y2);
    }
}
