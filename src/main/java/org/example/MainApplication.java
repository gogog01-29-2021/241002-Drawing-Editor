package org.example;

import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {
    private LayerManager layerManager = new LayerManager();
    private JLabel statusBar = new JLabel("0 objects.");

    public MainApplication() {
        setTitle("Shape Drawing Application");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout and add components
        setLayout(new BorderLayout());

        // Create the canvas and layer panel
        Canvas canvas = new Canvas(layerManager, statusBar);
        LayerPanel layerPanel = new LayerPanel(layerManager, canvas);
        Toolbar toolbar = new Toolbar(canvas);

        // Add components to the frame
        add(toolbar, BorderLayout.NORTH);       // Toolbar at the top
        add(layerPanel, BorderLayout.EAST);     // Layer panel at the right
        add(canvas, BorderLayout.CENTER);       // Canvas in the center

        // Set canvas drawing area white
        canvas.setBackground(Color.WHITE);

        // Add a status bar at the bottom
        add(statusBar, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Create and show the application window
        MainApplication app = new MainApplication();
        app.setVisible(true);
    }
}
