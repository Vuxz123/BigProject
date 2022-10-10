package com.ethnicthv.bigproject.asset;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

public class AnimatedChannelProvider {
    public static final AnimatedChannelProvider INSTANCE = new AnimatedChannelProvider();

    public ATex texture = new ATex();

    public final AnimationChannel PLAYER_IDLE = new AnimationChannel(texture.PLAYER_IDLE.getImage(), Duration.millis(900), 2);
    public final AnimationChannel PLAYER_WALK = new AnimationChannel(texture.PLAYER_WALK.getImage(), Duration.millis(900), 5);
    public final AnimationChannel COMMONENEMY = new AnimationChannel(texture.COMMONENEMY.getImage(), Duration.millis(200) , 7);
    public AnimatedChannelProvider() {
    }

    class ATex {
        public final Texture PLAYER_IDLE = FXGL.getAssetLoader().loadTexture("entity/player_idle.png", 32,32);
        public final Texture PLAYER_WALK = FXGL.getAssetLoader().loadTexture("entity/player_walk.png", 80,32);
        public final Texture COMMONENEMY = FXGL.getAssetLoader().loadTexture("entity/commonenemy.png", 112, 32);

        public ATex() {
        }
    }
}
