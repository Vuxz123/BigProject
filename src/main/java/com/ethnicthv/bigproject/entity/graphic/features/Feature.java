package com.ethnicthv.bigproject.entity.graphic.features;

import com.ethnicthv.bigproject.entity.graphic.FeaturedRendererComponent;
import com.ethnicthv.bigproject.entity.graphic.Renderer;

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
            Feature clone = (Feature) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
