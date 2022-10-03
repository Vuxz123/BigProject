package com.ethnicthv.bigproject.entity.graphic.features;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.time.LocalTimer;
import com.ethnicthv.bigproject.entity.graphic.Renderer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

public abstract class DurationFeature extends Feature {
    private BooleanProperty ispause = new SimpleBooleanProperty(false);
    private Duration duration;
    private LocalTimer localTimer = null;

    public DurationFeature(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void onAdd(Renderer renderer) {
        localTimer = FXGL.newLocalTimer();
        reset();
        localTimer.capture();
    }

    @Override
    public void onUpdate(Renderer renderer, double tpf) {
        if(ispause.get()) {
            return;
        }
        if (localTimer.elapsed(duration)) {
            rendererComponent.popFeature(this);
        }
    }

    @Override
    public void onRemove(Renderer renderer) {

    }

    public void pause() {
        this.ispause.setValue(true);
    }

    public void resume() {
        this.ispause.setValue(false);
    }

    public boolean ispause() {
        return this.ispause.get();
    }

    public abstract void reset();

}
