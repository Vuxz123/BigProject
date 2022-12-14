package com.ethnicthv.bigproject.entity.component.graphic.features;

import com.ethnicthv.bigproject.entity.component.graphic.FeaturedRendererComponent;
import com.ethnicthv.bigproject.entity.component.graphic.Renderer;

public abstract class Feature implements Cloneable {
    protected FeaturedRendererComponent rendererComponent = null;

    public abstract void onAdd(Renderer renderer);

    public abstract void onUpdate(Renderer renderer, double tpf);

    public abstract void onRemove(Renderer renderer);

    public void setRendererComponent(FeaturedRendererComponent rendererComponent) {
        this.rendererComponent = rendererComponent;
    }

    @Override
    public Feature clone() {
        try {
            return (Feature) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
