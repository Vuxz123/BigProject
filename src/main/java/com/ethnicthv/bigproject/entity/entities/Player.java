package com.ethnicthv.bigproject.entity.entities;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.ethnicthv.bigproject.asset.AnimatedChannelProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.entity.component.PlayerControlerComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.debug;
import static com.ethnicthv.bigproject.client.GameManager.grid;

public class Player extends Entity {

    public Player() {
        this.setType(EntityType.PLAYER);
    }

    public Entity getPlayer() {
//        entityBuilder()
//                .viewWithBBox(new Rectangle(16, 16, Color.BLUE))
//                .at(5 + 32, 5 + 32)
//                .with(new CustomCellMoveComponent(5, 5, 16, 16, 300))
//                .with(new CustomAStarMoveComponent(GameManager.grid.pfg))
//                .with(new GraphicComponent(new Rectangle(16, 30, Color.PINK)).setZIndex(2).setOffsetX(0))
//                .with(new BoundingBoxComponent())
//                .zIndex(1)
//                .anchorFromCenter()
//                .build();
        this.addComponent(new CustomCellMoveComponent(GameManager.OFFSETX, GameManager.OFFSETY, grid.gridsize, grid.gridsize, 300));
        this.addComponent(new CustomAStarMoveComponent(grid.pfg));
        this.addComponent(new AnimatedGraphicComponent(new AnimatedTexture(AnimatedChannelProvider.INSTANCE.PLAYER_IDLE))
                .setZIndex(1)
                .setOffsetX(-8)
                .setOffsetY(-22)
                .addChannel("walk", AnimatedChannelProvider.INSTANCE.PLAYER_WALK));
        this.addComponent(new CollidableComponent());
        this.addComponent(new PlayerControlerComponent());
        this.setZIndex(1);
        this.setPosition( GameManager.OFFSETX + grid.gridsize * 2, GameManager.OFFSETY + grid.gridsize * 2);
        this.setLocalAnchorFromCenter();
        this.getViewComponent().addChild(new Rectangle(16, 16, Color.BLUE));
        this.getViewComponent().setVisible(false);
        //this.getBoundingBoxComponent().addHitBox();


        this.getComponent(CustomCellMoveComponent.class).atDestinationProperty().addListener((o, old, isAtDestination) -> {
            if (isAtDestination) {
                debug("CellMoveComponent: reached destination");
            }
        });

        this.getComponent(CustomAStarMoveComponent.class).atDestinationProperty().addListener((o, old, isAtDestination) -> {
            if (isAtDestination) {
                debug("CustomAStarMoveComponent: reached destination");
            }
        });

        return this;
    }

}
