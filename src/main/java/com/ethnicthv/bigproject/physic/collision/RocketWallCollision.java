package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.entities.pools.ProjectilePools;
import javafx.geometry.Point2D;

public class RocketWallCollision extends CollisionHandler {
    public RocketWallCollision() {
        super(EntityType.WALL, EntityType.PLAYER);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
        FXGL.getGameWorld().getEntitiesByType(EntityType.PLAYER).remove(b);
        ProjectilePools.free(b);
        a.distance(b);
        Point2D c = a.getBoundingBoxComponent().getCenterWorld().subtract(b.getBoundingBoxComponent().getCenterWorld());
        Point2D d = b.getBoundingBoxComponent().getCenterWorld().subtract(c.multiply(0.5));
        FXGL.getGameWorld().spawn(("Explosion"), new SpawnData().put("x", d.getX()).put("y", d.getY()));
    }
}
