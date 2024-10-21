package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Toolbar extends JPanel {

    public Toolbar(JComponent canvas, JLabel statusBar) {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create buttons with icons
        JButton copyButton = createButton("Copy", "images/copy.png");
        JButton pasteButton = createButton("Paste", "images/paste.png");
        JButton deleteButton = createButton("Delete", "images/delete.png");
        JButton groupButton = createButton("Group", "images/group.png");

        // File operations buttons
        JButton newFileButton = createButton("New", "images/new_file.png");
        JButton saveFileButton = createButton("Save As", "images/save_file.png");
        JButton loadFileButton = createButton("Open", "images/load_file.png");

        // Divider between groups
        JSeparator separator1 = createSeparator();

        JButton curveButton = createButton("", "images/draws/line.png");
        JButton circleButton = createButton("", "images/draws/ellipse.png");
        JButton rectangleButton = createButton("", "images/draws/rectangle.png");
        JButton lineButton = createButton("color", null);

        // Divider between groups
        JSeparator separator2 = createSeparator();

        JButton undoButton = createButton("", "images/undo.png");
        JButton redoButton = createButton("", "images/redo.png");

        // Add buttons and dividers to the toolbar
        add(copyButton);
        add(pasteButton);
        add(deleteButton);
        add(groupButton);
        add(newFileButton);  // Add new file button
        add(saveFileButton);  // Add save file button
        add(loadFileButton);  // Add load file button
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
        addHoverEffect(newFileButton);  // Hover effect for new file button
        addHoverEffect(saveFileButton);  // Hover effect for save file button
        addHoverEffect(loadFileButton);  // Hover effect for load file button
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

        // Add actions for file operations
        addAction(newFileButton, () -> newFile(canvas, statusBar));
        addAction(saveFileButton, () -> saveFile(canvas, statusBar));
        addAction(loadFileButton, () -> loadFile(canvas, statusBar));
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

    // New File Action
    private void newFile(JComponent canvas, JLabel statusBar) {
        // Clear the canvas (or reset it to a default state)
        canvas.removeAll();
        canvas.repaint();
        statusBar.setText("New file created");
    }

    // Save File Action
    private void saveFile(JComponent canvas, JLabel statusBar) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                // Example: write some data from the canvas
                writer.write("Canvas content to save...");
                statusBar.setText("File saved: " + file.getName());
            } catch (IOException e) {
                e.printStackTrace();
                statusBar.setText("Error saving file");
            }
        }
    }

    // Load File Action
    private void loadFile(JComponent canvas, JLabel statusBar) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // Example: load some data into the canvas
                String content = new String(Files.readAllBytes(file.toPath()));
                statusBar.setText("File loaded: " + file.getName());
                // You can modify this to load the content into the canvas
            } catch (IOException e) {
                e.printStackTrace();
                statusBar.setText("Error loading file");
            }
        }
    }
}