package org.example;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<BaseShape> shapes;
    private boolean visible;

    public Layer() {
        shapes = new ArrayList<>();
        visible = true;
    }

    public List<BaseShape> getShapes() {
        return shapes;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void addShape(BaseShape shape) {
        shapes.add(shape);
    }

    public void removeShape(BaseShape shape) {
        shapes.remove(shape);
    }
}
