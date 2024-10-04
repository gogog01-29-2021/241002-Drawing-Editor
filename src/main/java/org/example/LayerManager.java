package org.example;

import java.util.ArrayList;
import java.util.List;

public class LayerManager {
    private List<Layer> layers;
    private int currentLayerIndex;  // This tracks the currently selected layer

    public LayerManager() {
        layers = new ArrayList<>();
        addNewLayer();  // Add an initial layer
    }

    // Add a new layer
    public void addNewLayer() {
        layers.add(new Layer("Layer " + (layers.size() + 1)));
        currentLayerIndex = layers.size() - 1;  // Set the new layer as the current layer
    }

    // Return the current active layer
    public Layer getActiveLayer() {
        return layers.get(currentLayerIndex);
    }

    // Get the index of the currently active layer
    public int getCurrentLayerIndex() {
        return currentLayerIndex;
    }

    // Set the current layer index (e.g., when user selects a different layer)
    public void setCurrentLayerIndex(int index) {
        if (index >= 0 && index < layers.size()) {
            currentLayerIndex = index;
        }
    }

    // Get all layers
    public List<Layer> getLayers() {
        return layers;
    }
}
