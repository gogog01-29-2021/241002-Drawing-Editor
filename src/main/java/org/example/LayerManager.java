package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LayerManager implements Serializable {
    private List<Layer> layers;
    private int activeLayerIndex;

    public LayerManager() {
        layers = new ArrayList<>();
        layers.add(new Layer()); // Start with one layer
        activeLayerIndex = 0;
    }

    public Layer getActiveLayer() {
        return layers.get(activeLayerIndex);
    }

    public void setActiveLayer(int index) {
        if (index >= 0 && index < layers.size()) {
            activeLayerIndex = index;
        }
    }

    public int getCurrentLayerIndex() {
        return activeLayerIndex;
    }

    public void addLayer() {
        layers.add(new Layer());
    }

    public void removeLayer(int index) {
        if (index >= 0 && index < layers.size()) {
            layers.remove(index);
            if (activeLayerIndex >= layers.size()) {
                activeLayerIndex = layers.size() - 1; // Adjust active layer index
            }
        }
    }

    public void moveLayer(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && fromIndex < layers.size() && toIndex >= 0 && toIndex < layers.size()) {
            Layer layer = layers.remove(fromIndex);
            layers.add(toIndex, layer);
        }
    }

    public List<Layer> getAllLayers() {
        return layers;
    }
}
