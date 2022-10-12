package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.entity.EntityType;

public class EtoShieldCollision extends CollisionHandler {
    public EtoShieldCollision() {
        super(EntityType.ENTITY, EntityType.SHIELD);
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        super.onCollision(a, b);
        a.removeFromWorld();
    }
}
