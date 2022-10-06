package com.ethnicthv.bigproject.asset;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

public class TextureProvider {
    public static final TextureProvider INSTANCE = new TextureProvider();
    public final Texture PLAYER = FXGL.getAssetLoader().loadTexture("player.png", 16,32);
    public final Texture FRAME = FXGL.getAssetLoader().loadTexture("frame.png");
    public final Texture BARFRAME = FXGL.getAssetLoader().loadTexture("barframe.png", 16* 13, 32);

    public final Texture EMBER = FXGL.getAssetLoader().loadTexture("ember.png",32,32);

    public final Texture ROCKET = FXGL.getAssetLoader().loadTexture("rocket.png", 100,20);

    public final Texture SHIELD_1 = FXGL.getAssetLoader().loadTexture("shield.png");

    public final Texture SHIELD_2 = FXGL.getAssetLoader().loadTexture("shield_layer2.png");

    public final Texture SHIELD_ICON = FXGL.getAssetLoader().loadTexture("shieldicon.png", 32, 32);
    public final Texture SPEED_ICON = FXGL.getAssetLoader().loadTexture("speedicon.png", 32, 32);

    private TextureProvider(){
    }


}
