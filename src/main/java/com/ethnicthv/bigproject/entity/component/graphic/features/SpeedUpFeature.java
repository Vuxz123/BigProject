package com.ethnicthv.bigproject.entity.component.graphic.features;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.time.LocalTimer;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.component.graphic.Renderer;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.MotionBlur;
import javafx.util.Duration;

import java.util.LinkedList;

public class SpeedUpFeature extends DurationFeature {

    private Duration delay = Duration.seconds(0.05);

    private LocalTimer timer = FXGL.newLocalTimer();
    private Entity entity;

    private LinkedList<Node> queue;

    public SpeedUpFeature() {
        super(Duration.seconds(10));
    }

    @Override
    public void onUpdate(Renderer renderer, double tpf) {
        if (timer.elapsed(delay)) {
            Texture cur = (Texture) queue.getLast();
            Point2D pos = GameManager.player.getGraphic().getGraphic().getPosition();
            cur.setTranslateX(pos.getX());
            cur.setTranslateY(pos.getY());
            if (GameManager.player.getComponent(CustomCellMoveComponent.class).isMovingLeft()) cur.setScaleX(-1);
            else cur.setScaleX(1);
            queue.addFirst(cur);
            queue.removeLast();
            timer.capture();
        }
    }

    @Override
    public void onAdd(Renderer renderer) {
        super.onAdd(renderer);
        if (queue == null) {
            entity = new Entity();
            queue = new LinkedList<>();
            for (int i = 0; i < 3; i++) {
                Texture t = TextureProvider.INSTANCE.PLAYER.copy();
                t.setEffect(new MotionBlur());
                entity.getViewComponent().addChild(t);
            }
            queue.addAll(entity.getViewComponent().getChildren());
            entity.setX(0);
            entity.setY(0);
        }
        FXGL.getGameWorld().addEntity(entity);
        timer.capture();
    }

    @Override
    public void onRemove(Renderer renderer) {
        super.onRemove(renderer);
        entity.removeFromWorld();
    }

    @Override
    public void reset() {
        queue = null;
        entity = null;
    }
}
