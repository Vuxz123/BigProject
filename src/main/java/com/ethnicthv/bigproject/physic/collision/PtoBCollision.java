package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import javafx.geometry.Point2D;

public class PtoBCollision extends CollisionHandler {
    public PtoBCollision() {
        super(EntityType.PLAYER, EntityType.BREAKABLE_BLOCK);
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        super.onCollision(a, b);
        HitBox boxA = a.getBoundingBoxComponent().hitBoxesProperty().get(0);
        HitBox boxB = b.getBoundingBoxComponent().hitBoxesProperty().get(0);
        Point2D point = boxA.getCenterWorld();
        point.add(8, 8);
        if (boxB.getCenterWorld().distance(point) <= 4) {
            GameManager.getPlayer().getPlayerData().dealDamage(100);
        }
    }
}
