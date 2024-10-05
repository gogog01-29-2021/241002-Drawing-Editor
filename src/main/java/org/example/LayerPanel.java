package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LayerPanel extends JPanel {
    private Canvas canvas;
    private LayerManager layerManager;
    private JList<String> layerList;

    public LayerPanel(LayerManager layerManager, Canvas canvas) {
        this.layerManager = layerManager;
        this.canvas = canvas;
        setLayout(new BorderLayout());

        layerList = new JList<>(new DefaultListModel<>());
        JScrollPane scrollPane = new JScrollPane(layerList);
        add(scrollPane, BorderLayout.CENTER);

        layerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = layerList.getSelectedIndex();
                canvas.highlightSelectedObject(index); // Ensure this is defined in Canvas
            }
        });
    }

    public void updateLayerList() {
        DefaultListModel<String> model = (DefaultListModel<String>) layerList.getModel();
        model.clear();
        for (int i = 0; i < layerManager.getActiveLayer().getShapes().size(); i++) {
            model.addElement("Object " + (i + 1));
        }
    }
}
