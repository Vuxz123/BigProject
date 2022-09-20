package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

public class OffscreenDelayCleanComponent extends Component {
    private Viewport viewport;
    private Duration duration;

    public OffscreenDelayCleanComponent(){
        this.viewport = FXGL.getGameScene().getViewport();
        this.duration = Duration.millis(1000);
    }

    public OffscreenDelayCleanComponent(double duration) {
        this.viewport = FXGL.getGameScene().getViewport();
        this.duration = Duration.millis(duration);
    }

    public OffscreenDelayCleanComponent(Viewport viewport, double duration) {
        this.viewport = viewport;
        this.duration = Duration.millis(duration);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        if(entity.isActive()) {
            if (entity.getBoundingBoxComponent().isOutside(viewport.getVisibleArea())) {
                FXGL.getGameTimer().runOnceAfter(entity::removeFromWorld, duration);
                pause();
            }
        }
    }
}
