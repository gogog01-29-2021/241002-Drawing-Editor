package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayerPanel extends JPanel {
    private LayerManager layerManager;
    private JList<String> layerList;
    private DefaultListModel<String> listModel;
    private JButton addLayerButton;

    public LayerPanel(LayerManager layerManager) {
        this.layerManager = layerManager;

        // List to display layer names
        listModel = new DefaultListModel<>();
        layerList = new JList<>(listModel);
        updateLayerList();

        // Button to add a new layer
        addLayerButton = new JButton("Add Layer");
        addLayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layerManager.addNewLayer();
                updateLayerList();
            }
        });

        // List selection listener to change the current layer
        layerList.addListSelectionListener(e -> {
            int selectedIndex = layerList.getSelectedIndex();
            if (selectedIndex >= 0) {
                layerManager.setCurrentLayerIndex(selectedIndex);
            }
        });

        // Layout for the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JScrollPane(layerList));
        add(addLayerButton);
    }

    // Method to update the displayed layer list
    private void updateLayerList() {
        listModel.clear();
        for (int i = 0; i < layerManager.getLayers().size(); i++) {
            listModel.addElement("Layer " + (i + 1));
        }
        layerList.setSelectedIndex(layerManager.getCurrentLayerIndex());
    }
}
