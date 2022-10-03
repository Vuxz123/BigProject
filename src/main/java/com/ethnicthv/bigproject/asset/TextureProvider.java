package com.ethnicthv.bigproject.asset;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

public class TextureProvider {
    public static final TextureProvider INSTANCE = new TextureProvider();

    public final Texture FRAME = FXGL.getAssetLoader().loadTexture("frame.png");

    public final Texture EMBER = FXGL.getAssetLoader().loadTexture("ember.png");

    public final Texture ROCKET = FXGL.getAssetLoader().loadTexture("rocket.png", 100,20);

    public final Texture SHIELD_1 = FXGL.getAssetLoader().loadTexture("shield.png");

    public final Texture SHIELD_2 = FXGL.getAssetLoader().loadTexture("shield_layer2.png");

    public Texture PLAYER;

    private TextureProvider(){
    }


}
