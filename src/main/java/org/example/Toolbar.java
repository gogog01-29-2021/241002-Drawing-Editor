package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Toolbar extends JPanel {
    private Canvas canvas;

    public Toolbar(Canvas canvas) {
        this.canvas = canvas;
        setBackground(Color.LIGHT_GRAY);

        JButton saveButton = createButton("Save");
        JButton loadButton = createButton("Load");
        JButton lineButton = createButton("Line");
        JButton rectangleButton = createButton("Rectangle");
        JButton circleButton = createButton("Circle");
        JButton copyButton = createButton("Copy");
        JButton pasteButton = createButton("Paste");
        JButton deleteButton = createButton("Delete");
        JButton changeColorButton = createButton("Change Color");

        saveButton.addActionListener(e -> saveCanvas());
        loadButton.addActionListener(e -> loadCanvas());

        lineButton.addActionListener(e -> canvas.setCurrentTool("line"));
        rectangleButton.addActionListener(e -> canvas.setCurrentTool("rectangle"));
        circleButton.addActionListener(e -> canvas.setCurrentTool("circle"));
        copyButton.addActionListener(e -> canvas.copyShape());
        pasteButton.addActionListener(e -> canvas.pasteShape());
        deleteButton.addActionListener(e -> canvas.deleteShape());
        changeColorButton.addActionListener(e -> canvas.openColorPicker());

        add(saveButton);
        add(loadButton);
        add(lineButton);
        add(rectangleButton);
        add(circleButton);
        add(copyButton);
        add(pasteButton);
        add(deleteButton);
        add(changeColorButton);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 30));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(Color.DARK_GRAY);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });

        return button;
    }

    private void saveCanvas() {
        // TODO: Implement save functionality
        System.out.println("Save Canvas");
    }

    private void loadCanvas() {
        // TODO: Implement load functionality
        System.out.println("Load Canvas");
    }
}
