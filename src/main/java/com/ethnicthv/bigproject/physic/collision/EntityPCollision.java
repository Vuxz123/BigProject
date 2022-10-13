package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.play;
import static com.ethnicthv.bigproject.asset.Config.Asset;

public class EntityPCollision extends CollisionHandler {
    public EntityPCollision() {
        super(EntityType.ENTITY, EntityType.PARTICLE);
    }

    @Override
    protected void onCollisionBegin(Entity entity, Entity particle) {
        super.onCollisionBegin(entity, particle);
        HitBox boxA = entity.getBoundingBoxComponent().hitBoxesProperty().get(0);
        HitBox boxB = particle.getBoundingBoxComponent().hitBoxesProperty().get(0);
        Point2D point = boxA.getCenterWorld();
        point.add(8, 8);
        if (boxB.getCenterWorld().distance(point) <= 8.8) {
            entity.removeFromWorld();
            particle.removeFromWorld();
            GameManager.data.killed++;
            play(Asset.SOUND_LOSE_LIFE);
        }
    }
}
