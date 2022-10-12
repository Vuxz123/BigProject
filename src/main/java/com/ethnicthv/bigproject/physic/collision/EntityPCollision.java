package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.client.GameData;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.entities.pools.ProjectilePools;
import javafx.geometry.Point2D;

public class EntityPCollision extends CollisionHandler {
    public EntityPCollision() {
        super(EntityType.ENTITY, EntityType.PARTICLE);
    }
    @Override
    protected void onCollisionBegin(Entity entity, Entity particle) {
        super.onCollisionBegin(entity, particle);
        entity.removeFromWorld();
        particle.removeFromWorld();
        GameManager.data.killed ++;
    }
}
