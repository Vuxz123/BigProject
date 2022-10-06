package com.ethnicthv.bigproject.entity.component.graphic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

public class StaticGraphicComponent extends GraphicComponent {
    public StaticGraphicComponent(Node texture) {
        super(texture);
    }

    @Override
    public BooleanProperty pausedProperty() {
        return new SimpleBooleanProperty(true);
    }
}
