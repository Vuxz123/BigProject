package com.ethnicthv.bigproject.entity.component.graphic;

import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import java.util.HashMap;

public class AnimatedGraphicComponent extends GraphicComponent{
    private final AnimatedTexture animatedTexture;

    private final HashMap<String,AnimationChannel> channels = new HashMap<>();

    private String cchannel = "idle";

    public AnimatedGraphicComponent(AnimatedTexture texture) {
        super(texture);
        this.animatedTexture = texture;
        addChannel("idle", texture.getAnimationChannel());
    }

    @Override
    public void onAdded() {
        super.onAdded();
        animatedTexture.loop();
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }

    @Override
    public AnimatedGraphicComponent setZIndex(int z) {
        return (AnimatedGraphicComponent) super.setZIndex(z);
    }

    @Override
    public AnimatedGraphicComponent setOffsetX(int offsetX) {
        return (AnimatedGraphicComponent) super.setOffsetX(offsetX);
    }

    @Override
    public AnimatedGraphicComponent setOffsetY(int offsetY) {
        return (AnimatedGraphicComponent) super.setOffsetY(offsetY);
    }

    public AnimatedGraphicComponent addChannel(String name, AnimationChannel channel) {
        channels.put(name,channel);
        return this;
    }

    public void playChannel(String name) {
        cchannel = name;
        animatedTexture.loopNoOverride(channels.get(cchannel));
    }
}
