package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

public class OffscreenDelayCleanComponent extends Component {
    private Viewport viewport;
    private Duration duration;

    public OffscreenDelayCleanComponent(double duration) {
        this.viewport = FXGL.getGameScene().getViewport();
        this.duration = Duration.millis(duration);
    }

    public OffscreenDelayCleanComponent(Viewport viewport, double duration) {
        this.viewport = viewport;
        this.duration = Duration.millis(duration);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        if (entity.getBoundingBoxComponent().isOutside(viewport.getVisibleArea())) {
            FXGL.getGameTimer().runOnceAfter(entity::removeFromWorld, duration);
            pause();
        }
    }
}
