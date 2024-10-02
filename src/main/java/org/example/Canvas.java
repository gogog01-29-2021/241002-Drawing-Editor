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

    public Canvas() {
        shapes = new ArrayList<>();

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
        // Depending on the current tool, create the appropriate shape
        if (currentTool.equals("line")) {
            tempShape = new Line(e.getX(), e.getY(), e.getX(), e.getY()); // Instantiate org.example.Line
        } else if (currentTool.equals("rectangle")) {
            tempShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY());
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
            shapes.add(tempShape);  // Add the completed shape to the list
            tempShape = null;  // Reset the temporary shape
        }
    }

    // Draw all shapes, including the shape currently being drawn (if any)
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

    // Method for the toolbar to set the current tool
    public void setCurrentTool(String tool) {
        this.currentTool = tool;
    }
} // <---- Make sure this closing brace is present to close the class
