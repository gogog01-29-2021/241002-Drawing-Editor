package org.example;

import javax.swing.*;
import java.awt.*;

public class MainApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Drawing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        LayerManager layerManager = new LayerManager();
        JLabel statusBar = new JLabel("0 objects.");
        Canvas canvas = new Canvas(layerManager, statusBar);

        // Create toolbar
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false); // Make the toolbar fixed

        // Add buttons
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton lineButton = new JButton("Line");
        JButton rectangleButton = new JButton("Rectangle");
        JButton circleButton = new JButton("Circle");
        JButton copyButton = new JButton("Copy");
        JButton pasteButton = new JButton("Paste");
        JButton deleteButton = new JButton("Delete");
        JButton changeColorButton = new JButton("Change Color");

        // Add hover effects
        addHoverEffect(saveButton);
        addHoverEffect(loadButton);
        addHoverEffect(lineButton);
        addHoverEffect(rectangleButton);
        addHoverEffect(circleButton);
        addHoverEffect(copyButton);
        addHoverEffect(pasteButton);
        addHoverEffect(deleteButton);
        addHoverEffect(changeColorButton);

        // Add buttons to toolbar
        toolbar.add(saveButton);
        toolbar.add(loadButton);
        toolbar.add(lineButton);
        toolbar.add(rectangleButton);
        toolbar.add(circleButton);
        toolbar.add(copyButton);
        toolbar.add(pasteButton);
        toolbar.add(deleteButton);
        toolbar.add(changeColorButton);

        // Make toolbar scrollable
        JScrollPane toolbarScrollPane = new JScrollPane(toolbar);
        toolbarScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(toolbarScrollPane, BorderLayout.NORTH);

        // Layer panel on the right
        LayerPanel layerPanel = new LayerPanel(layerManager, canvas);
        frame.add(layerPanel, BorderLayout.EAST);

        // Add canvas and status bar
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(statusBar, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Helper method to add hover effect to buttons
    private static void addHoverEffect(JButton button) {
        Color defaultColor = button.getBackground();
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY); // On hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor); // Reset after hover
            }
        });
    }
}
