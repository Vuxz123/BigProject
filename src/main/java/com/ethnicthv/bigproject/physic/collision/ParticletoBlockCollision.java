package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.entity.EntityType;

public class ParticletoBlockCollision extends CollisionHandler {
    public ParticletoBlockCollision() {
        super(EntityType.PARTICLE, EntityType.BREAKABLE_BLOCK);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
        b.removeFromWorld();
    }
}
