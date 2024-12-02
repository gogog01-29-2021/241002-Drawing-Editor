package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;

public class Canvas extends JPanel {
    private String currentTool = "select";
    private BaseShape tempShape;
    private BaseShape selectedShape;
    private Color currentColor = BaseShape.getDefaultColor();
    private int lastX, lastY;
    private final LayerManager layerManager;
    private final JLabel statusBar;
    private final LayerPanel layerPanel;
    private SelectionRectangle selectionRectangle;

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

    // Override paintComponent to draw both permanent shapes and the placeholder line
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all permanent shapes
        for (BaseShape shape : layerManager.getActiveLayer().getShapes()) {
            shape.draw(g);
        }

        // Highlight the selected shape, if any
        if (selectedShape != null) {
            selectedShape.highlight(g);
        }

        // Draw the temporary shape (line preview) if it exists
        if (tempShape != null) {
            tempShape.draw(g);  // This shows the placeholder line while dragging
        }

        if (selectionRectangle != null) {
            selectionRectangle.draw(g);
        }
    }

    private void handleMousePressed(MouseEvent e) {
        if ("select".equals(currentTool)) {
            selectionRectangle = new SelectionRectangle(e.getX(), e.getY(), Color.BLACK);
        } else {
            selectedShape = null;
            // Loop through shapes in the active layer to check for selection
            for (BaseShape shape : layerManager.getActiveLayer().getShapes()) {
                if (shape.contains(e.getX(), e.getY())) {
                    selectedShape = shape;
                    lastX = e.getX();
                    lastY = e.getY();
                    return; // Early exit if a shape is selected
                }
            }

            // If no shape is selected, and a drawing tool is active, create a new shape
            if (currentTool != null) {
                switch (currentTool) {
                    case "line":
                        tempShape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
                        break;
                    case "rectangle":
                        tempShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
                        break;
                    case "circle":
                        tempShape = new Circle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
                        break;
                    default:
                        tempShape = null; // Ensure tempShape is null if the tool is unrecognized
                        break;
                }
            }
        }
        repaint(); // Trigger repaint to update the UI
    }


    private void handleMouseDragged(MouseEvent e) {
        if (selectionRectangle != null) {
            selectionRectangle.setEndCoordinates(e.getX(), e.getY());
            selectionRectangle.updateSelection(layerManager.getActiveLayer().getShapes());
        } else if (selectedShape != null) {
            selectedShape.moveBy(e.getX() - lastX, e.getY() - lastY);
            lastX = e.getX();
            lastY = e.getY();
        } else if (tempShape != null) {
            tempShape.setEndCoordinates(e.getX(), e.getY());
        }
        repaint();
    }

    private void handleMouseReleased(MouseEvent e) {
        if (selectionRectangle != null) {
//            List<BaseShape> selectedShapes = selectionRectangle.getSelectedShapes();
//            if (!selectedShapes.isEmpty()) {
//                // Do something with selected shapes, like copying them
//                for (BaseShape shape : selectedShapes) {
//                    // Copy or select shapes
//                    layerManager.getActiveLayer().addShape(shape.copy());
//                }
//            }
            selectionRectangle = null; // Reset selection rectangle
        } else if (tempShape != null) {
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

    public void unselect() {
        selectedShape = null;
    }

    public void copyShape() {
        if (selectedShape != null) {
            tempShape = selectedShape.copy();
        }
    }

    public void pasteShape() {
        if (tempShape != null) {
            BaseShape copiedShape = tempShape.copy();
            tempShape = null;
            layerManager.getActiveLayer().addShape(copiedShape);
            layerPanel.updateLayerList();
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
        if (selectedShape != null) {
            statusBar.setText("Selected: " + selectedShape.getName() + " at " + selectedShape.getBounds());
        } else {
            statusBar.setText(layerManager.getActiveLayer().getShapes().size() + " objects.");
        }
    }
}
