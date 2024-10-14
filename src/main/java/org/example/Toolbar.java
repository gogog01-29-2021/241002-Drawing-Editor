package org.example;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private JButton saveButton, loadButton, lineButton, rectangleButton, circleButton, copyButton, pasteButton, deleteButton, changeColorButton;
    private Canvas canvas;

    public Toolbar(Canvas canvas) {
        this.canvas = canvas;

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create panel to hold buttons and set layout to BoxLayout (compact horizontal layout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Create buttons and replace with icons only (no text)
        saveButton = createButtonWithIcon("/images/draws/save.png");
        loadButton = createButtonWithIcon("/images/draws/file.png");
        lineButton = createButtonWithIcon("/images/draws/line.png");
        rectangleButton = createButtonWithIcon("/images/draws/rectangle.png");
        circleButton = createButtonWithIcon("/images/draws/ellipse.png");
        copyButton = createButtonWithIcon("/images/draws/copy.png");
        pasteButton = createButtonWithIcon("/images/draws/paste.png");
        deleteButton = createButtonWithIcon("/images/draws/delete.png");
        changeColorButton = createButtonWithIcon("/images/draws/color.png");

        // Add buttons to the buttonPanel
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(lineButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(circleButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(pasteButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(changeColorButton);

        // Add action listeners to buttons
        saveButton.addActionListener(e -> canvas.save());
        loadButton.addActionListener(e -> canvas.load());
        lineButton.addActionListener(e -> canvas.setCurrentTool("line"));
        rectangleButton.addActionListener(e -> canvas.setCurrentTool("rectangle"));
        circleButton.addActionListener(e -> canvas.setCurrentTool("circle"));
        copyButton.addActionListener(e -> canvas.copyShape());
        pasteButton.addActionListener(e -> canvas.pasteShape());
        deleteButton.addActionListener(e -> canvas.deleteShape());
        changeColorButton.addActionListener(e -> canvas.openColorPicker());

        // Add hover effect to the buttons (change background on hover)
        addHoverEffect(saveButton);
        addHoverEffect(loadButton);
        addHoverEffect(lineButton);
        addHoverEffect(rectangleButton);
        addHoverEffect(circleButton);
        addHoverEffect(copyButton);
        addHoverEffect(pasteButton);
        addHoverEffect(deleteButton);
        addHoverEffect(changeColorButton);

        // Create a scrollable panel
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Only horizontal scroll

        // Add the scrollable panel to the toolbar
        add(scrollPane, BorderLayout.CENTER);
    }

    // Helper method to create button with icon only
    private JButton createButtonWithIcon(String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        JButton button = new JButton(icon);
        button.setText(""); // Ensure no text is shown
        button.setContentAreaFilled(false); // Makes the button more flat
        button.setFocusPainted(false); // Removes the focus outline
        button.setBorderPainted(false); // Removes the border
        return button;
    }

    // Helper method to add hover effect to the buttons
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });
    }
}
