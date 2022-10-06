package com.ethnicthv.bigproject.entity.component.graphic;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.Node;

import java.util.List;

public class Renderer extends Entity {
    public void addLayer(Node layer) {
        this.getViewComponent().getChildren().add(layer);
    }

    public List<Node> getLayers() {
        return this.getViewComponent().getChildren();
    }
}
