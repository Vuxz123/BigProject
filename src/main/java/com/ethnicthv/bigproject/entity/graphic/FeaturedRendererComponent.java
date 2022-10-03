package com.ethnicthv.bigproject.entity.graphic;

import com.ethnicthv.bigproject.entity.graphic.features.Feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

public class FeaturedRendererComponent extends GraphicComponent {
    private final Renderer renderer;
    private final List<Feature> features = Collections.synchronizedList(new ArrayList<>());

    public FeaturedRendererComponent() {
        super(new Renderer());
        renderer = (Renderer) super.getGraphic();
    }

    public void pushFeature(Feature feature) {
        if (!features.contains(feature)) {
            this.features.add(feature);
            feature.setRendererComponent(this);
            feature.onAdd(renderer);
        }
    }

    public void popFeature(Feature feature) {
        if (feature != null) {
            feature.onRemove(renderer);
            synchronized (features) {
                features.remove(feature);
            }
        }
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        this.render(tpf);
    }

    private void render(double tpf) {
        synchronized (features) {
            try {
                for(Feature feature: features) feature.onUpdate(renderer, tpf);
            }catch (ConcurrentModificationException ignored){
            }
        }
    }
}
