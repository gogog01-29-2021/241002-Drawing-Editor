package org.example;

import javax.swing.*;
import java.awt.*;

public class Toolbar {
    private JToolBar toolbar;
    private Canvas canvas;

    public Toolbar(Canvas canvas) {
        this.canvas = canvas;
        toolbar = new JToolBar();
        toolbar.setFloatable(false);

        // Add buttons
        addButton("Save", e -> canvas.save());
        addButton("Load", e -> canvas.load());
        addButton("Line", e -> canvas.setCurrentTool("line"));
        addButton("Rectangle", e -> canvas.setCurrentTool("rectangle"));
        addButton("Circle", e -> canvas.setCurrentTool("circle"));
        addButton("Copy", e -> canvas.copyShape());
        addButton("Paste", e -> canvas.pasteShape());
        addButton("Delete", e -> canvas.deleteShape());
        addButton("Change Color", e -> canvas.openColorPicker());

        // Hover effects
        for (Component c : toolbar.getComponents()) {
            if (c instanceof JButton) {
                addHoverEffect((JButton) c);
            }
        }
    }

    private void addButton(String title, java.awt.event.ActionListener action) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        toolbar.add(button);
    }

    private void addHoverEffect(JButton button) {
        Color defaultColor = button.getBackground();
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor);
            }
        });
    }

    public JToolBar getToolbar() {
        return toolbar;
    }
}
