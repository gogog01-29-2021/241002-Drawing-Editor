package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private String currentTool = "select";
    private BaseShape tempShape;
    private BaseShape selectedShape;
    private Color currentColor = BaseShape.getDefaultColor();
    private int lastX, lastY;
    private LayerManager layerManager;
    private JLabel statusBar;
    private LayerPanel layerPanel;
    private java.awt.Rectangle selectionRectangle = null;

    private List<BaseShape> selectedShapes = new ArrayList<>();

    public Canvas(LayerManager layerManager, JLabel statusBar, LayerPanel layerPanel) {
        this.layerManager = layerManager;
        this.statusBar = statusBar;
        this.layerPanel = layerPanel;

        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all layers
        for (Layer layer : layerManager.getAllLayers()) {
            for (BaseShape shape : layer.getShapes()) {
                shape.draw(g);
            }
        }

        // Draw temporary shape
        if (tempShape != null) {
            tempShape.draw(g);
        }

        // Draw selection rectangle
        if (selectionRectangle != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(1));
            g2.draw(selectionRectangle);
            g2.setColor(new Color(0, 0, 255, 50)); // Semi-transparent fill
            g2.fill(selectionRectangle);
        }
    }

    private void handleMousePressed(MouseEvent e) {
        if (currentTool.equals("select")) {
            // Check if the click is inside any shape
            for (BaseShape shape : layerManager.getActiveLayer().getShapes()) {
                if (shape.contains(e.getX(), e.getY())) {
                    selectedShape = shape; // Prioritize dragging the shape
                    lastX = e.getX();
                    lastY = e.getY();
                    return;
                }
            }

            // If no shape is clicked, start the selection rectangle
            selectionRectangle = new java.awt.Rectangle(e.getX(), e.getY(), 0, 0);
        } else {
            // Handle other tools (e.g., line, rectangle, circle)
            if (currentTool.equals("line")) {
                tempShape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
            } else if (currentTool.equals("rectangle")) {
                tempShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
            } else if (currentTool.equals("circle")) {
                tempShape = new Circle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
            }
        }
    }


    private void handleMouseDragged(MouseEvent e) {
        if (selectedShape != null) {
            // Dragging the selected shape
            selectedShape.moveBy(e.getX() - lastX, e.getY() - lastY);
            lastX = e.getX();
            lastY = e.getY();
        } else if (selectionRectangle != null) {
            // Update the bounds of the selection rectangle
            selectionRectangle.setBounds(
                    Math.min((int)selectionRectangle.getX(), e.getX()),
                    Math.min((int)selectionRectangle.getY(), e.getY()),
                    Math.abs((int)selectionRectangle.getX() - e.getX()),
                    Math.abs((int)selectionRectangle.getY() - e.getY())
            );
        } else if (tempShape != null) {
            // Dragging a temporary shape
            tempShape.setEndCoordinates(e.getX(), e.getY());
        }
        repaint();
    }


    private void handleMouseReleased(MouseEvent e) {
        if (selectedShape != null) {
            // Finished dragging the shape
            selectedShape = null;
        } else if (selectionRectangle != null) {
            // Select shapes that intersect with the rectangle
            selectedShapes.clear();
            for (BaseShape shape : layerManager.getActiveLayer().getShapes()) {
                if (selectionRectangle.intersects(shape.getBoundingBox())) {
                    selectedShapes.add(shape);
                }
            }
            selectionRectangle = null; // Clear the selection rectangle
            updateStatusBarWithSelection();
        } else if (tempShape != null) {
            // Add the temporary shape to the active layer
            layerManager.getActiveLayer().addShape(tempShape);
            layerPanel.updateLayerList();
            tempShape = null;
        }
        repaint();
    }


    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("shapes.dat"))) {
            out.writeObject(layerManager.getActiveLayer().getShapes());
            JOptionPane.showMessageDialog(this, "Shapes saved successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving shapes: " + ex.getMessage());
        }
    }

    public void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("shapes.dat"))) {
            List<BaseShape> shapes = (List<BaseShape>) in.readObject();
            layerManager.getActiveLayer().setShapes(shapes);
            repaint();
            JOptionPane.showMessageDialog(this, "Shapes loaded successfully!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error loading shapes: " + ex.getMessage());
        }
    }

    public void setCurrentTool(String tool) {
        currentTool = tool;
    }

    public void copyShape() {
        if (selectedShape != null) {
            tempShape = selectedShape.copy();
        }
    }

    public void pasteShape() {
        if (tempShape != null) {
            BaseShape copiedShape = tempShape.copy();
            copiedShape.moveBy(10, 10);
            layerManager.getActiveLayer().addShape(copiedShape);
            updateStatusBar();
            repaint();
        }
    }

    public void deleteShape() {
        if (selectedShape != null) {
            layerManager.getActiveLayer().removeShape(selectedShape);
            selectedShape = null;
            updateStatusBar();
            repaint();
        }
    }

    public void openColorPicker() {
        Color newColor = JColorChooser.showDialog(this, "Pick a Color", currentColor);
        if (newColor != null) {
            currentColor = newColor;
            BaseShape.setDefaultColor(newColor);
        }
    }

    private void updateStatusBar() {
        statusBar.setText(layerManager.getActiveLayer().getShapes().size() + " objects.");
    }

    private void updateStatusBarWithSelection() {
        if (selectedShapes.isEmpty()) {
            statusBar.setText("No shapes selected.");
        } else {
            statusBar.setText(selectedShapes.size() + " shapes selected.");
        }
    }

    public void highlightSelectedObject(int index) {
        if (index >= 0 && index < layerManager.getActiveLayer().getShapes().size()) {
            selectedShape = layerManager.getActiveLayer().getShapes().get(index);
            repaint();
        }
    }
    public void clearSelection() {
        selectedShape = null;
        selectionRectangle = null;
        selectedShapes.clear();
        repaint();
        updateStatusBar();
    }

}
