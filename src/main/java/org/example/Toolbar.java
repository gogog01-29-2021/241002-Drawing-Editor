package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Toolbar extends JPanel {

    public Toolbar(JComponent canvas, JLabel statusBar) {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create buttons with icons
        JButton copyButton = createButton("Copy", "images/copy.png");
        JButton pasteButton = createButton("Paste", "images/paste.png");
        JButton deleteButton = createButton("Delete", "images/delete.png");
        JButton groupButton = createButton("Group", "images/group.png");

        // Divider between groups
        JSeparator separator1 = createSeparator();

        JButton curveButton = createButton("", "images/draws/line.png");
        JButton circleButton = createButton("", "images/draws/ellipse.png");
        JButton rectangleButton = createButton("", "images/draws/rectangle.png");
        JButton lineButton = createButton("Line", null);

        // Divider between groups
        JSeparator separator2 = createSeparator();

        JButton undoButton = createButton("", "images/undo.png");
        JButton redoButton = createButton("", "images/redo.png");

        // Add buttons and dividers to the toolbar
        add(copyButton);
        add(pasteButton);
        add(deleteButton);
        add(groupButton);
        add(separator1);  // Add first divider
        add(curveButton);
        add(circleButton);
        add(rectangleButton);
        add(lineButton);
        add(separator2);  // Add second divider
        add(undoButton);
        add(redoButton);

        // Add hover effects to buttons
        addHoverEffect(copyButton);
        addHoverEffect(pasteButton);
        addHoverEffect(deleteButton);
        addHoverEffect(groupButton);
        addHoverEffect(curveButton);
        addHoverEffect(circleButton);
        addHoverEffect(rectangleButton);
        addHoverEffect(lineButton);
        addHoverEffect(undoButton);
        addHoverEffect(redoButton);

        // Add actions to buttons (example placeholder)
        addAction(copyButton, () -> statusBar.setText("Copy clicked"));
        addAction(pasteButton, () -> statusBar.setText("Paste clicked"));
        addAction(deleteButton, () -> statusBar.setText("Delete clicked"));
        addAction(lineButton, () -> statusBar.setText("Line clicked"));
    }

    // Utility method to create a button with enforced padding and a visible border
    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton();
        if (iconPath != null) {
            button.setIcon(new ImageIcon(iconPath));
        }
        button.setText(text);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Text below the icon

        // Set a visible border around the button and add padding using EmptyBorder
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),  // Button border
                new EmptyBorder(5, 5, 5, 5)              // Padding inside the border
        ));

        button.setBackground(Color.WHITE);  // Set a white background for the button

        return button;
    }

    // Utility method to add a visible separator
    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(5, 40));  // Increased width for better visibility
        separator.setForeground(Color.BLACK);  // Set separator color
        separator.setOpaque(true);            // Ensure separator is not transparent
        return separator;
    }

    // Utility method to add actions to a button
    private void addAction(JButton button, Runnable action) {
        button.addActionListener(e -> action.run());
    }

    // Utility method to add hover effects to a button
    private void addHoverEffect(JButton button) {
        Color defaultColor = button.getBackground();
        button.setOpaque(true);
        button.setBorderPainted(true);  // Ensure the border is painted

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY); // Change background on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor); // Reset to default after hover
            }
        });
    }
}
