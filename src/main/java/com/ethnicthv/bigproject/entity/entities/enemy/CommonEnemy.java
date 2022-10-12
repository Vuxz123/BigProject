package com.ethnicthv.bigproject.entity.entities.enemy;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.pool.Poolable;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.state.StateComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.ethnicthv.bigproject.asset.AnimatedChannelProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.ai.AIComponent;
import com.ethnicthv.bigproject.entity.component.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.entity.component.graphic.GraphicComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomRandomAStarMoveComponent;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class CommonEnemy extends Entity implements Poolable {

    public CommonEnemy() {

        this.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.circle(8)));
        this.setType(EntityType.ENTITY);
        this.setLocalAnchorFromCenter();
        this.addComponent(new CustomCellMoveComponent(GameManager.OFFSETX, GameManager.OFFSETY ,GameManager.grid.gridsize, GameManager.grid.gridsize, 150));
        this.addComponent(new CustomAStarMoveComponent(GameManager.grid.pfg));
        this.addComponent(new CustomRandomAStarMoveComponent(Duration.seconds(0), Duration.seconds(0.5)));
        this.addComponent(new StateComponent());
        this.addComponent(new AIComponent());
        this.addComponent(new CollidableComponent(true));
        this.addComponent(new AnimatedGraphicComponent(new AnimatedTexture(AnimatedChannelProvider.INSTANCE.COMMONENEMY)).setOffsetY(-16));

        this.getComponent(AnimatedGraphicComponent.class).addEffect(new DropShadow(), 0);
    }

    @Override
    public void reset() {

    }
}
