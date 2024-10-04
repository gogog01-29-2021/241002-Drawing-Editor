package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel {
    private String currentTool = "select";
    private BaseShape tempShape;
    private BaseShape selectedShape;  // To store the selected shape for dragging or resizing
    private Color currentColor = Color.BLACK;
    private int lastX, lastY;  // To store the last mouse position for dragging
    private LayerManager layerManager;
    private JLabel statusBar;

    public Canvas(LayerManager layerManager, JLabel statusBar) {
        this.layerManager = layerManager;
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

    public void openColorPicker() {
        Color newColor = JColorChooser.showDialog(this, "Pick a Color", currentColor);
        if (newColor != null) {
            currentColor = newColor;
        }
    }

    private void handleMousePressed(MouseEvent e) {
        // Check if the user clicks on an existing shape
        for (BaseShape shape : layerManager.getActiveLayer().getShapes()) {
            if (shape.contains(e.getX(), e.getY())) {
                selectedShape = shape;
                lastX = e.getX();
                lastY = e.getY();
                return;
            }
        }

        // If no shape is selected, create a new shape
        if (currentTool.equals("line")) {
            tempShape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
        } else if (currentTool.equals("rectangle")) {
            tempShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
        } else if (currentTool.equals("circle")) {
            tempShape = new Circle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        // While dragging to draw a new shape
        if (tempShape != null) {
            tempShape.setEndCoordinates(e.getX(), e.getY());
        } else if (selectedShape != null) {
            // Move the selected shape
            selectedShape.moveBy(e.getX() - lastX, e.getY() - lastY);
            lastX = e.getX();
            lastY = e.getY();
        }
        repaint();
    }

    private void handleMouseReleased(MouseEvent e) {
        if (tempShape != null) {
            // Add the completed shape to the active layer
            layerManager.getActiveLayer().addShape(tempShape);
            tempShape = null;  // Reset the temporary shape
            updateStatusBar();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all shapes
        for (BaseShape shape : layerManager.getActiveLayer().getShapes()) {
            shape.draw(g);
        }

        // Highlight selected shape
        if (selectedShape != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED); // Highlight color
            g2d.setStroke(new BasicStroke(3)); // Thicker stroke for highlighting
            selectedShape.draw(g2d); // Re-draw the selected shape with highlighting
        }

        // Draw the temporary shape (during drawing)
        if (tempShape != null) {
            tempShape.draw(g);
        }
    }

    public void setCurrentTool(String tool) {
        this.currentTool = tool;
    }

    public void copyShape() {
        if (selectedShape != null) {
            tempShape = selectedShape.copy();
        }
    }

    public void pasteShape() {
        if (tempShape != null) {
            BaseShape copiedShape = tempShape.copy();
            copiedShape.moveBy(10, 10); // Offset the shape for pasting
            layerManager.getActiveLayer().addShape(copiedShape);
            updateStatusBar();
            repaint();
        }
    }

    public void deleteShape() {
        if (selectedShape != null) {
            layerManager.getActiveLayer().getShapes().remove(selectedShape);
            selectedShape = null;
            updateStatusBar();
            repaint();
        }
    }

    public void highlightSelectedObject(int index) {
        if (index >= 0 && index < layerManager.getActiveLayer().getShapes().size()) {
            selectedShape = layerManager.getActiveLayer().getShapes().get(index);
            repaint();
        }
    }

    private void updateStatusBar() {
        statusBar.setText(layerManager.getActiveLayer().getShapes().size() + " objects.");
    }
}
