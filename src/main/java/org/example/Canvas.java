package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel {
    private String currentTool = "select";
    private BaseShape tempShape;
    private Color currentColor = Color.BLACK;
    private LayerManager layerManager;
    private JLabel statusBar;
    private LayerPanel layerPanel; // Reference to update the list

    public Canvas(LayerManager layerManager, JLabel statusBar, LayerPanel layerPanel) {
        this.layerManager = layerManager;
        this.statusBar = statusBar;
        this.layerPanel = layerPanel;

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

    // Open color picker to change the color of new shapes
    public void openColorPicker() {
        Color newColor = JColorChooser.showDialog(this, "Pick a Color", currentColor);
        if (newColor != null) {
            currentColor = newColor;
        }
    }

    private void handleMousePressed(MouseEvent e) {
        // Depending on the current tool, create the appropriate shape
        if (currentTool.equals("line")) {
            tempShape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
        } else if (currentTool.equals("rectangle")) {
            tempShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (tempShape != null) {
            tempShape.setEndCoordinates(e.getX(), e.getY());
            repaint();
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        if (tempShape != null) {
            // Add the completed shape as a new layer
            layerManager.addShapeAsLayer(tempShape);
            tempShape = null;  // Reset the temporary shape
            updateStatusBar();
            repaint();
            layerPanel.updateLayerList();  // Update the right-side panel with new object
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw all shapes (layers)
        for (BaseShape shape : layerManager.getLayers()) {
            shape.draw(g);
        }

        if (tempShape != null) {
            tempShape.draw(g);
        }
    }

    public void setCurrentTool(String tool) {
        this.currentTool = tool;
    }

    public void copyShape() {
        if (layerManager.getActiveShape() != null) {
            tempShape = layerManager.getActiveShape().copy();
        }
    }

    public void pasteShape() {
        if (tempShape != null) {
            BaseShape copiedShape = tempShape.copy();
            copiedShape.moveBy(10, 10); // Offset the shape for pasting
            layerManager.addShapeAsLayer(copiedShape);
            updateStatusBar();
            repaint();
            layerPanel.updateLayerList();  // Update the right-side panel with new object
        }
    }

    public void deleteShape() {
        layerManager.removeActiveShape();
        updateStatusBar();
        repaint();
        layerPanel.updateLayerList();  // Update the right-side panel after deletion
    }

    private void updateStatusBar() {
        statusBar.setText(layerManager.getLayers().size() + " objects.");
    }
}
