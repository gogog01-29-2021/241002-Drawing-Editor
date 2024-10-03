package org.example;

import javax.swing.*;
import java.util.List;

public class LayerPanel extends JPanel {
    private LayerManager layerManager;
    private JList<String> layerList;
    private DefaultListModel<String> listModel;

    public LayerPanel(LayerManager layerManager) {
        this.layerManager = layerManager;

        // List to display layer names
        listModel = new DefaultListModel<>();
        layerList = new JList<>(listModel);
        updateLayerList();

        // List selection listener to change the current layer (object)
        layerList.addListSelectionListener(e -> {
            int selectedIndex = layerList.getSelectedIndex();
            if (selectedIndex >= 0) {
                layerManager.setCurrentLayerIndex(selectedIndex);
            }
        });

        // Layout for the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JScrollPane(layerList));
    }

    // Method to update the displayed layer list
    public void updateLayerList() {
        listModel.clear();
        List<BaseShape> layers = layerManager.getLayers();
        for (int i = 0; i < layers.size(); i++) {
            listModel.addElement("Object " + (i + 1));  // Display each shape as "Object X"
        }
        layerList.setSelectedIndex(layerManager.getCurrentLayerIndex());
    }
}
