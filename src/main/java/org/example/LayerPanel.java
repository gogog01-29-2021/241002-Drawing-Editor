package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LayerPanel extends JPanel {
    private LayerManager layerManager;
    private JList<String> layerList;

    public LayerPanel(LayerManager layerManager) {
        this.layerManager = layerManager;
        setLayout(new BorderLayout());

        // Darker gray background for the layer panel
        setBackground(Color.DARK_GRAY);

        layerList = new JList<>(new DefaultListModel<>());
        layerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(layerList);
        add(scrollPane, BorderLayout.CENTER);

//        layerList.addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting()) {
//                int index = layerList.getSelectedIndex();
//                canvas.highlightSelectedObject(index);
//            }
//        });
    }

    public void updateLayerList() {
        DefaultListModel<String> model = (DefaultListModel<String>) layerList.getModel();
        model.clear();
        List<BaseShape> shapes = layerManager.getActiveLayer().getShapes(); // Get the list of shapes
        for (int i = 0; i < shapes.size(); i++) {
            BaseShape shape = shapes.get(i); // Get the shape at index i
            model.addElement(shape.getName() + ", element: " + (i + 1)); // Add index and name
        }
    }
}
