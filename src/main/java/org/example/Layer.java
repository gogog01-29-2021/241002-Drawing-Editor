package org.example;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<BaseShape> shapes;
    private String name;

    public Layer(String name) {
        this.name = name;
        shapes = new ArrayList<>();
    }

    public List<BaseShape> getShapes() {
        return shapes;
    }

    public void addShape(BaseShape shape) {
        shapes.add(shape);
    }

    public String getName() {
        return name;
    }
}
