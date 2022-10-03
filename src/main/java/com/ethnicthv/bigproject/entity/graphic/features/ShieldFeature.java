package com.ethnicthv.bigproject.entity.graphic.features;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.entity.graphic.Layer;
import com.ethnicthv.bigproject.entity.graphic.Renderer;
import javafx.util.Duration;

public class ShieldFeature extends DurationFeature{

    private Entity SHIELD1;
    private Entity SHIELD2;

    public ShieldFeature(Duration duration) {
        super(duration);
    }

    @Override
    public void onAdd(Renderer renderer) {
        super.onAdd(renderer);
        SHIELD1 = FXGL.entityBuilder().view(TextureProvider.INSTANCE.SHIELD_1.copy()).at(renderer.getPosition().add(-32,-48)).build();
        SHIELD2 = FXGL.entityBuilder().view(TextureProvider.INSTANCE.SHIELD_2.copy()).at(renderer.getPosition().add(-32,-48)).build();
        SHIELD1.setZIndex(3);
        SHIELD2.setZIndex(5);
        FXGL.getGameWorld().addEntity(SHIELD1);
        FXGL.getGameWorld().addEntity(SHIELD2);
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
        SHIELD1.removeFromWorld();
        SHIELD2.removeFromWorld();
    }

    @Override
    public void reset() {
        return;
    }
}
