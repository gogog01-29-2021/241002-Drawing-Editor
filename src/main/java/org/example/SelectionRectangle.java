package org.example;

import java.awt.*;

public class SelectionRectangle extends BaseShape {
    private int x1, y1, x2, y2;

    public SelectionRectangle(int x1, int y1) {
        super(x1, y1, x1, y1, Color.GRAY); // Initially, the end coordinates are the same.
        this.x1 = x1;
        this.y1 = y1;
    }

    public String getName() {
        return "DrawingRectangle";
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1)); // Thin stroke for dashed line
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set dashed line
        float[] dashPattern = {10.0f, 5.0f}; // Dash length and gap length
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0.0f));

        g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) &&
                y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
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
        return new SelectionRectangle(x1, y1);
    }

    @Override
    public void highlight(Graphics g) {
        // Highlighting dashed rectangle isn't necessary, it is already visually distinct
    }

    @Override
    public String getBounds() {
        return "Selection rectangle";
    }
}
