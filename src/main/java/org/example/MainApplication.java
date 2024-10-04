package org.example;

import javax.swing.*;
import java.awt.*;

public class MainApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Drawing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        LayerManager layerManager = new LayerManager();
        JLabel statusBar = new JLabel("0 objects.");

        // Canvas and Layer Panel
        Canvas canvas = new Canvas(layerManager, statusBar);
        LayerPanel layerPanel = new LayerPanel(layerManager, canvas);

        // Toolbar setup
        Toolbar toolbar = new Toolbar(canvas);

        // Set up layout
        frame.setLayout(new BorderLayout());
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.add(layerPanel, BorderLayout.EAST);  // Add the layer panel to the right side

        frame.setVisible(true);
    }
}
