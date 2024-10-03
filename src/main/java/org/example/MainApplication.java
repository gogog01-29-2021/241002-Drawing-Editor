package org.example;

import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {
    private Toolbar toolbar;
    private Canvas canvas;
    private LayerManager layerManager;
    private LayerPanel layerPanel;
    private JLabel statusBar;

    public MainApplication() {
        setTitle("Shape Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Initialize LayerManager and LayerPanel
        layerManager = new LayerManager();
        layerPanel = new LayerPanel(layerManager);

        // Create status bar
        statusBar = new JLabel("0 objects.");

        // Initialize canvas and toolbar, pass the required components
        canvas = new Canvas(layerManager, statusBar, layerPanel);
        toolbar = new Toolbar(canvas);

        // Layout setup: Add the toolbar, canvas, and layer panel to the main window
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(layerPanel, BorderLayout.EAST);
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Start the application in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new MainApplication());
    }
}
