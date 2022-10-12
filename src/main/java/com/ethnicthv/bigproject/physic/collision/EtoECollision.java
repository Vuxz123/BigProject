package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;

public class EtoECollision extends CollisionHandler {
    public EtoECollision() {
        super(EntityType.ENTITY, EntityType.ENTITY);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
        a.getComponent(CustomCellMoveComponent.class).pause();
    }

    @Override
    protected void onCollisionEnd(Entity a, Entity b) {
        super.onCollisionEnd(a, b);
        try {
            a.getComponent(CustomCellMoveComponent.class).resume();
        } catch (IllegalArgumentException ignored) {
        }
    }
}
