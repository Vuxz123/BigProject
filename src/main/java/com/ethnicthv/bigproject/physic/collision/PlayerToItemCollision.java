package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.PickupableComponent;

public class PlayerToItemCollision extends CollisionHandler {
    public PlayerToItemCollision() {
        super(EntityType.PLAYER, EntityType.ITEMENTITY);
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        super.onCollision(a, b);
        if(b.getComponent(PickupableComponent.class).isPickupable()) {
            System.out.println("Pick Up");
            b.removeFromWorld();
        }
    }
}
