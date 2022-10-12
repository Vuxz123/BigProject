package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.entity.EntityType;

public class ParticletoBlockCollision extends CollisionHandler {
    public ParticletoBlockCollision() {
        super(EntityType.BREAKABLE_BLOCK, EntityType.PARTICLE);
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
        a.removeFromWorld();
    }
}
