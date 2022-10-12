package com.ethnicthv.bigproject.asset;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

public class TextureProvider {
    public static final TextureProvider INSTANCE = new TextureProvider();

    public final Texture BLOCK = FXGL.getAssetLoader().loadTexture("entity/block.png", 16, 16);
    public final Texture WALL = FXGL.getAssetLoader().loadTexture("entity/wall.png", 16, 16);
    public final Texture GRASS = FXGL.getAssetLoader().loadTexture("entity/grass.png", 16, 16);
    public final Texture PLAYER = FXGL.getAssetLoader().loadTexture("ui/player.png", 16, 32);
    public final Texture FRAME = FXGL.getAssetLoader().loadTexture("ui/frame.png");
    public final Texture BARFRAME = FXGL.getAssetLoader().loadTexture("ui/barframe.png", 16 * 13, 32);

    public final Texture EMBER = FXGL.getAssetLoader().loadTexture("particle/ember.png", 32, 32);
    public final Texture BLAST = FXGL.getAssetLoader().loadTexture("particle/blast.png", 32, 32);

    public final Texture ROCKET = FXGL.getAssetLoader().loadTexture("rocket.png", 100, 20);

    public final Texture SHIELD_1 = FXGL.getAssetLoader().loadTexture("feature/shield.png");

    public final Texture SHIELD_2 = FXGL.getAssetLoader().loadTexture("feature/shield_layer2.png");
    public final Texture HEALTH = FXGL.getAssetLoader().loadTexture("ui/healthbottle.png", 32, 32);
    public final Texture MANA = FXGL.getAssetLoader().loadTexture("ui/manabottle.png", 32, 32);
    public final Texture SHIELD_ICON = FXGL.getAssetLoader().loadTexture("ui/shieldicon.png", 32, 32);
    public final Texture SPEED_ICON = FXGL.getAssetLoader().loadTexture("ui/speedicon.png", 32, 32);

    public final Texture BLOCK_ICON = FXGL.getAssetLoader().loadTexture("ui/blockicon.png", 32, 32);

    public final Texture BOOM_ICON = FXGL.getAssetLoader().loadTexture("ui/boomicon.png", 32, 32);

    private TextureProvider() {
    }


}
