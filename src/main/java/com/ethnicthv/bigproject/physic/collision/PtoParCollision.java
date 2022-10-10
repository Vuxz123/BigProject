package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;

public class PtoParCollision extends CollisionHandler {
    public PtoParCollision() {
        super(EntityType.PLAYER, EntityType.PARTICLE);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
        b.removeFromWorld();
        if(GameManager.getPlayer().getPlayerData().dealDamage(25)) {
            FXGL.getWindowService().startNewGame();
        }
    }
}
