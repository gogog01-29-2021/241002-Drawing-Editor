package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel {
    private JButton selectButton;
    private JButton lineButton;
    private JButton rectangleButton;
    private Canvas canvas;

    // Constructor accepting the canvas
    public Toolbar(Canvas canvas) {
        this.canvas = canvas;  // Link toolbar to canvas

        // Initialize buttons
        selectButton = new JButton("Select");
        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");

        // Add buttons to toolbar
        add(selectButton);
        add(lineButton);
        add(rectangleButton);

        // Action Listeners for each tool
        selectButton.addActionListener(new ToolActionListener("select"));
        lineButton.addActionListener(new ToolActionListener("line"));
        rectangleButton.addActionListener(new ToolActionListener("rectangle"));
    }

    // Inner class for handling tool selection
    private class ToolActionListener implements ActionListener {
        private String toolType;

        public ToolActionListener(String toolType) {
            this.toolType = toolType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // When a tool is selected, tell the canvas to change the current tool
            canvas.setCurrentTool(toolType);
        }
    }
}
