package org.example;

import java.util.ArrayList;
import java.util.List;

public class LayerManager {
    private List<List<BaseShape>> layers;
    private int currentLayerIndex;

    public LayerManager() {
        layers = new ArrayList<>();
        addNewLayer(); // Start with one layer
    }

    public void addNewLayer() {
        layers.add(new ArrayList<>());
        currentLayerIndex = layers.size() - 1;
    }

    public void setCurrentLayerIndex(int index) {
        currentLayerIndex = index;
    }

    public int getCurrentLayerIndex() {
        return currentLayerIndex;
    }

    public List<List<BaseShape>> getLayers() {
        return layers;
    }

    public List<BaseShape> getCurrentLayerShapes() {
        return layers.get(currentLayerIndex);
    }
}
