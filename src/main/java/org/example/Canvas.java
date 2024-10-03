package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private List<BaseShape> shapes;
    private String currentTool = "select";
    private BaseShape tempShape;
    private BaseShape copiedShape;
    private JLabel statusBar;

    public Canvas(LayerManager layerManager, JLabel statusBar) {
        shapes = new ArrayList<>();
        this.statusBar = statusBar;

        // Mouse Listener for drawing shapes
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });
    }

    // Mouse event handlers
    private void handleMousePressed(MouseEvent e) {
        if (currentTool.equals("line")) {
            tempShape = new Line(e.getX(), e.getY(), e.getX(), e.getY());
        } else if (currentTool.equals("rectangle")) {
            tempShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY());
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (tempShape != null) {
            tempShape.moveBy(e.getX() - tempShape.x2, e.getY() - tempShape.y2);
            repaint();
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        if (tempShape != null) {
            shapes.add(tempShape);
            tempShape = null;
            updateStatusBar();
        }
    }

    // Drawing all shapes, including the one being drawn
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (BaseShape shape : shapes) {
            shape.draw(g);
        }
        if (tempShape != null) {
            tempShape.draw(g);
        }
    }

    // Copy, paste, delete functionalities
    public void copyShape() {
        if (!shapes.isEmpty()) {
            copiedShape = shapes.get(shapes.size() - 1).copy();
        }
    }

    public void pasteShape() {
        if (copiedShape != null) {
            copiedShape.moveBy(10, 10);
            shapes.add(copiedShape);
            updateStatusBar();
            repaint();
        }
    }

    public void deleteShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            updateStatusBar();
            repaint();
        }
    }

    public void openColorPicker() {
        Color newColor = JColorChooser.showDialog(this, "Pick a Color", Color.BLACK);
        if (newColor != null) {
            BaseShape.setDefaultColor(newColor);
        }
    }

    public void setCurrentTool(String tool) {
        this.currentTool = tool;
    }

    private void updateStatusBar() {
        statusBar.setText(shapes.size() + " entities");
    }
}
