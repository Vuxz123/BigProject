package com.ethnicthv.bigproject.entity.component.graphic.features;

import com.almasb.fxgl.entity.Entity;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.graphic.Renderer;
import javafx.animation.Interpolator;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ShieldFeature extends DurationFeature{

    private Entity SHIELD1;
    private Entity SHIELD2;

    public ShieldFeature(Duration duration) {
        super(duration);
    }

    @Override
    public void onAdd(Renderer renderer) {
        super.onAdd(renderer);
        GameManager.getPlayer().getPCC().setInvincible(true);
        GameManager.getPlayer().getPlayerData().resetShieldDelay();
        SHIELD1 = entityBuilder().view(TextureProvider.INSTANCE.SHIELD_1.copy()).at(renderer.getPosition().add(-32,-48)).build();
        SHIELD2 = entityBuilder().view(TextureProvider.INSTANCE.SHIELD_2.copy()).at(renderer.getPosition().add(-32,-48)).build();
        SHIELD1.setZIndex(3);
        SHIELD2.setZIndex(5);
        getGameWorld().addEntity(SHIELD1);
        getGameWorld().addEntity(SHIELD2);
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .duration(Duration.seconds(1))
                .scale(SHIELD1)
                .origin(SHIELD1.getCenter())
                .from(new Point2D(0,0))
                .to(new Point2D(1,1))
                .buildAndPlay();
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .duration(Duration.seconds(1))
                .fadeIn(SHIELD1).buildAndPlay();
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .duration(Duration.seconds(1))
                .scale(SHIELD2).origin(SHIELD2.getCenter()).from(new Point2D(0,0)).to(new Point2D(1,1))
                .buildAndPlay();
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .duration(Duration.seconds(1))
                .fadeIn(SHIELD2).buildAndPlay();
    }

    @Override
    public void onUpdate(Renderer renderer, double tpf) {
        super.onUpdate(renderer, tpf);
        SHIELD2.setPosition(renderer.getPosition().add(-32,-48));
        SHIELD1.setPosition(renderer.getPosition().add(-32,-48));
    }

    @Override
    public void onRemove(Renderer renderer) {
        super.onRemove(renderer);
        GameManager.getPlayer().getPCC().setInvincible(false);
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .onFinished(() -> SHIELD1.removeFromWorld())
                .duration(Duration.seconds(1))
                .scale(SHIELD1).from(new Point2D(1,1)).to(new Point2D(0,0))
                .buildAndPlay();
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .duration(Duration.seconds(1))
                .fadeOut(SHIELD1).buildAndPlay();
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .onFinished(() -> SHIELD2.removeFromWorld())
                .duration(Duration.seconds(1))
                .scale(SHIELD2).from(new Point2D(1,1)).to(new Point2D(0,0))
                .buildAndPlay();
        animationBuilder()
                .interpolator(Interpolator.EASE_IN)
                .duration(Duration.seconds(1))
                .fadeOut(SHIELD2).buildAndPlay();
    }

    @Override
    public void reset() {
    }
}
