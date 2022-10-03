package com.ethnicthv.bigproject.entity.graphic;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;

public class Layer extends Entity {
    public Layer(Texture texture) {
        this.getViewComponent().getChildren().add(texture);
    }
}
