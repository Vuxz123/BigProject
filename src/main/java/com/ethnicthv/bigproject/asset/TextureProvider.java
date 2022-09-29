package com.ethnicthv.bigproject.asset;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

public class TextureProvider {
    public static final TextureProvider INSTANCE = new TextureProvider();
    public final Texture EMBER;
    public final Texture ROCKET;



    public Texture PLAYER;

    private TextureProvider(){
        EMBER = FXGL.getAssetLoader().loadTexture("ember.png");
        ROCKET = FXGL.getAssetLoader().loadTexture("rocket.png", 100,20);
    }


}
