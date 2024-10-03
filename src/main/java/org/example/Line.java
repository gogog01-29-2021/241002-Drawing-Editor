package org.example;

import java.awt.*;

public class Line extends BaseShape {
    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public BaseShape copy() {
        return new Line(x1, y1, x2, y2);
    }
}
