package org.example;

import javax.swing.*;

public class Toolbar extends JPanel {
    private Canvas canvas;

    public Toolbar(Canvas canvas) {
        this.canvas = canvas;

        // Create buttons for tools
        JButton selectButton = new JButton("Select");
        JButton lineButton = new JButton("Line");
        JButton rectangleButton = new JButton("Rectangle");
        JButton copyButton = new JButton("Copy");
        JButton pasteButton = new JButton("Paste");
        JButton deleteButton = new JButton("Delete");
        JButton changeColorButton = new JButton("Change Color");

        // Add action listeners to set the tool in the canvas
        selectButton.addActionListener(e -> canvas.setCurrentTool("select"));
        lineButton.addActionListener(e -> canvas.setCurrentTool("line"));
        rectangleButton.addActionListener(e -> canvas.setCurrentTool("rectangle"));
        copyButton.addActionListener(e -> canvas.copyShape());
        pasteButton.addActionListener(e -> canvas.pasteShape());
        deleteButton.addActionListener(e -> canvas.deleteShape());
        changeColorButton.addActionListener(e -> canvas.openColorPicker());

        // Add buttons to the toolbar
        add(selectButton);
        add(lineButton);
        add(rectangleButton);
        add(copyButton);
        add(pasteButton);
        add(deleteButton);
        add(changeColorButton);
    }
}
