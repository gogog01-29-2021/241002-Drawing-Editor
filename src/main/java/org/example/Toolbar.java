package org.example;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    public Toolbar(Canvas canvas) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.LIGHT_GRAY);

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton lineButton = new JButton("Line");
        JButton rectangleButton = new JButton("Rectangle");
        JButton circleButton = new JButton("Circle");
        JButton copyButton = new JButton("Copy");
        JButton pasteButton = new JButton("Paste");
        JButton deleteButton = new JButton("Delete");
        JButton changeColorButton = new JButton("Change Color");

        // Button actions
        saveButton.addActionListener(e -> canvas.save());
        loadButton.addActionListener(e -> canvas.load());
        lineButton.addActionListener(e -> canvas.setCurrentTool("line"));
        rectangleButton.addActionListener(e -> canvas.setCurrentTool("rectangle"));
        circleButton.addActionListener(e -> canvas.setCurrentTool("circle"));
        copyButton.addActionListener(e -> canvas.copyShape());
        pasteButton.addActionListener(e -> canvas.pasteShape());
        deleteButton.addActionListener(e -> canvas.deleteShape());
        changeColorButton.addActionListener(e -> canvas.openColorPicker());

        // Adding buttons to the toolbar
        this.add(saveButton);
        this.add(loadButton);
        this.add(lineButton);
        this.add(rectangleButton);
        this.add(circleButton);
        this.add(copyButton);
        this.add(pasteButton);
        this.add(deleteButton);
        this.add(changeColorButton);
    }
}
