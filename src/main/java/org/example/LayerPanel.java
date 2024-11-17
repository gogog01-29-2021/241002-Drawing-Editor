package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LayerPanel extends JPanel {
    private LayerManager layerManager;
    private JList<String> layerList;
    private JButton addLayerButton, deleteLayerButton, moveUpButton, moveDownButton;

    public LayerPanel(LayerManager layerManager) {
        this.layerManager = layerManager;

        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        layerList = new JList<>(new DefaultListModel<>());
        layerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(layerList);

        layerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = layerList.getSelectedIndex();
                layerManager.setActiveLayer(selectedIndex);
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        updateLayerList();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        addLayerButton = new JButton("Add Layer");
        deleteLayerButton = new JButton("Delete Layer");
        moveUpButton = new JButton("Move Up");
        moveDownButton = new JButton("Move Down");

        // Button listeners
        addLayerButton.addActionListener(e -> {
            layerManager.addLayer();
            updateLayerList();
        });

        deleteLayerButton.addActionListener(e -> {
            int selectedIndex = layerList.getSelectedIndex();
            layerManager.removeLayer(selectedIndex);
            updateLayerList();
        });

        moveUpButton.addActionListener(e -> {
            int selectedIndex = layerList.getSelectedIndex();
            if (selectedIndex > 0) {
                layerManager.moveLayer(selectedIndex, selectedIndex - 1);
                updateLayerList();
                layerList.setSelectedIndex(selectedIndex - 1);
            }
        });

        moveDownButton.addActionListener(e -> {
            int selectedIndex = layerList.getSelectedIndex();
            if (selectedIndex < layerManager.getAllLayers().size() - 1) {
                layerManager.moveLayer(selectedIndex, selectedIndex + 1);
                updateLayerList();
                layerList.setSelectedIndex(selectedIndex + 1);
            }
        });

        // Add buttons to the panel
        panel.add(addLayerButton);
        panel.add(deleteLayerButton);
        panel.add(moveUpButton);
        panel.add(moveDownButton);

        return panel;
    }

    public void updateLayerList() {
        DefaultListModel<String> model = (DefaultListModel<String>) layerList.getModel();
        model.clear();
        List<Layer> layers = layerManager.getAllLayers();
        for (int i = 0; i < layers.size(); i++) {
            model.addElement("Layer " + (i + 1));
        }
        layerList.setSelectedIndex(layerManager.getCurrentLayerIndex());
    }
}
