package org.example;

import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {
    private Toolbar toolbar;
    private Canvas canvas;

    public MainApplication() {
        setTitle("Shape Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Initialize canvas and toolbar
        canvas = new Canvas();
        toolbar = new Toolbar(canvas);

        // Layout setup: Add the toolbar and the canvas to the main window
        getContentPane().add(toolbar, "North");
        getContentPane().add(canvas, "Center");

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Start the application in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new MainApplication());
    }
}
