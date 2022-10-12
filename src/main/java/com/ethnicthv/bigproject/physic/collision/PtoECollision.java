package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;

public class PtoECollision extends CollisionHandler {
    public PtoECollision() {
        super(EntityType.PLAYER, EntityType.ENTITY);
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        super.onCollision(a, b);
        GameManager.getPlayer().getPlayerData().dealDamage(25);
        if(GameManager.getPlayer().getPCC().isInvincible()) return;
        b.removeFromWorld();
    }
}
