package org.example;

import java.util.ArrayList;
import java.util.List;

public class LayerManager {
    private List<BaseShape> layers;  // Each "layer" corresponds to a shape
    private int currentLayerIndex;

    public LayerManager() {
        layers = new ArrayList<>();
        currentLayerIndex = -1;  // Start with no layer selected
    }

    // Add a new shape as a layer
    public void addShapeAsLayer(BaseShape shape) {
        layers.add(shape);  // Each shape becomes a new layer
        currentLayerIndex = layers.size() - 1;  // Set the current layer to the new shape
    }

    public BaseShape getActiveShape() {
        if (currentLayerIndex >= 0 && currentLayerIndex < layers.size()) {
            return layers.get(currentLayerIndex);  // Return the currently active shape
        }
        return null;
    }

    public void setCurrentLayerIndex(int index) {
        if (index >= 0 && index < layers.size()) {
            currentLayerIndex = index;
        }
    }

    public int getCurrentLayerIndex() {
        return currentLayerIndex;
    }

    public List<BaseShape> getLayers() {
        return layers;
    }

    public void removeActiveShape() {
        if (currentLayerIndex >= 0 && currentLayerIndex < layers.size()) {
            layers.remove(currentLayerIndex);  // Remove the currently active shape
            currentLayerIndex = layers.size() - 1;  // Set to the last layer
        }
    }
}
