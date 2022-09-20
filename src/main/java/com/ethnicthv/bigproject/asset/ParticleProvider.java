package com.ethnicthv.bigproject.asset;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.particle.ParticleEmitter;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.core.math.FXGLMath.random;

public class ParticleProvider {
    public static final ParticleProvider INSTANCE = new ParticleProvider();
    private ParticleProvider(){

    }

    public ParticleEmitter getEmberEmitter(Point2D dir, Point2D pos){
        ParticleEmitter res = new ParticleEmitter();
        res.setStartColor(Color.rgb(255, 255, 90));
        res.setEndColor(Color.rgb(230, 75, 40));
        res.setSize(5, 8);
        res.setVelocityFunction(i -> {

            return dir.multiply(FXGLMath.random(1,1.5));
        });
        res.setSpawnPointFunction(i -> new Point2D(0, 0).add(new Point2D(i * (FXGLMath.randomDouble() - 0.5), FXGLMath.randomDouble() - 1)));
        res.setExpireFunction(i -> Duration.seconds(1));
        res.setBlendMode(BlendMode.SRC_OVER);
        res.setEmissionRate(0.5);
        res.setNumParticles(4);
        res.setMaxEmissions(100);
        res.setSourceImage(TextureProvider.INSTANCE.EMBER);
        return res;
    }
}
