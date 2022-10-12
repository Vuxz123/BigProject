package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.entities.pools.ProjectilePools;
import javafx.geometry.Point2D;

public class PARTICLEtWallCollision extends CollisionHandler {
    public PARTICLEtWallCollision() {
        super(EntityType.WALL, EntityType.PARTICLE);
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        super.onCollision(a, b);
        HitBox boxA = a.getBoundingBoxComponent().hitBoxesProperty().get(0);
        HitBox boxB = b.getBoundingBoxComponent().hitBoxesProperty().get(0);
        Point2D point = boxA.getCenterWorld();
        if (boxB.getCenterWorld().distance(point) <= 6.6) {
            b.removeFromWorld();
        }
    }
}
