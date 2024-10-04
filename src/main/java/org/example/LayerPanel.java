package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LayerPanel extends JPanel {
    private JList<String> layerList;
    private DefaultListModel<String> listModel;
    private Canvas canvas;

    public LayerPanel(LayerManager layerManager, Canvas canvas) {
        this.canvas = canvas;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 600));
        setBackground(Color.LIGHT_GRAY);  // Make the panel slightly gray

        listModel = new DefaultListModel<>();
        layerList = new JList<>(listModel);
        layerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Update canvas when a layer is selected
        layerList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = layerList.getSelectedIndex();
                if (selectedIndex != -1) {
                    canvas.highlightSelectedObject(selectedIndex);
                }
            }
        });

        add(new JScrollPane(layerList), BorderLayout.CENTER);
    }

    public void addLayer(String layerName) {
        listModel.addElement(layerName);
    }

    public void removeLayer(int index) {
        listModel.remove(index);
    }

    public void updateLayerList(String[] layerNames) {
        listModel.removeAllElements();
        for (String name : layerNames) {
            listModel.addElement(name);
        }
    }
}
