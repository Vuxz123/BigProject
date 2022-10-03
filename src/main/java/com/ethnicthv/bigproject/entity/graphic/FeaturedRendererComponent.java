package com.ethnicthv.bigproject.entity.graphic;

import com.ethnicthv.bigproject.entity.graphic.GraphicComponent;

public class FeaturedRendererComponent extends GraphicComponent {
    private Renderer renderer;
    private Feature feature;

    public FeaturedRendererComponent() {
        super(new Renderer());
        renderer = (Renderer) super.entity;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
        feature.onAdd(renderer);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        this.render(tpf);
    }

    private void render(double tpf) {
        feature.render(renderer, tpf);
    }
}
