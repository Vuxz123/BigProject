package com.ethnicthv.bigproject.physic.collision;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.PickupableComponent;
import javafx.animation.Interpolator;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class PlayerToItemCollision extends CollisionHandler {
    public PlayerToItemCollision() {
        super(EntityType.PLAYER, EntityType.ITEMENTITY);
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        super.onCollision(a, b);
        if(b.getComponent(PickupableComponent.class).isPickupable()) {
            System.out.println("Pick Up");
            b.getComponent(PickupableComponent.class).pickupableProperty().setValue(false);
            FXGL.animationBuilder(FXGL.getGameScene()).interpolator(Interpolator.EASE_IN)
                    .onFinished(b::removeFromWorld)
                    .duration(Duration.seconds(2))
                    .scale(b.getViewComponent().getChildren()).from(new Point2D(0,0)).to(new Point2D(5,5)).buildAndPlay();
            FXGL.animationBuilder(FXGL.getGameScene()).interpolator(Interpolator.EASE_OUT)
                    .duration(Duration.seconds(2))
                    .translate(b.getViewComponent().getChildren()).from(new Point2D(0,0)).to(FXGL.getAppCenter().subtract(b.getPosition())).buildAndPlay();
        }
    }
}
