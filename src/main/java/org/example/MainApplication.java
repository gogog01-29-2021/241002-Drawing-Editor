package org.example;

import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {
    private Canvas canvas;
    private LayerManager layerManager;
    private LayerPanel layerPanel;
    private JLabel statusBar;

    public MainApplication() {
        setTitle("Shape Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        // Initialize layer manager and side panel
        layerManager = new LayerManager();
        statusBar = new JLabel("0 entities");
        layerPanel = new LayerPanel(layerManager);
        canvas = new Canvas(layerManager, statusBar);

        // Create the toolbar
        Toolbar toolbar = new Toolbar(canvas);

        // Layout setup: Add the toolbar, canvas, and layer panel to the main window
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(layerPanel, BorderLayout.EAST);
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApplication());
    }
}
