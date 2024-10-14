package org.example;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        // Create and set up the main application frame
        JFrame frame = new JFrame("Shape Drawing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout()); // Set layout manager

        // Layer manager to handle layers of shapes
        LayerManager layerManager = new LayerManager();

        // Status bar at the bottom to show information about objects
        JLabel statusBar = new JLabel("0 objects.");

        // Canvas for drawing shapes, linked to the layer manager and status bar
        Canvas canvas = new Canvas(layerManager, statusBar);

        // Create the toolbar and make it scrollable
        Toolbar toolbar = new Toolbar(canvas, statusBar);
        JScrollPane toolbarScrollPane = new JScrollPane(toolbar);
        toolbarScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Layer panel on the right to manage layers
        LayerPanel layerPanel = new LayerPanel(layerManager, canvas);

        // Add components to the frame
        frame.add(toolbarScrollPane, BorderLayout.NORTH);  // Toolbar at the top
        frame.add(layerPanel, BorderLayout.EAST);          // Layer panel on the right
        frame.add(canvas, BorderLayout.CENTER);            // Canvas in the center
        frame.add(statusBar, BorderLayout.SOUTH);          // Status bar at the bottom

        // Make the frame visible
        frame.setVisible(true);
    }
}
