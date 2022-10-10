package com.ethnicthv.bigproject.entity.entities;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.ethnicthv.bigproject.asset.AnimatedChannelProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.PlayerData;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.entity.component.PlayerControlerComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import com.ethnicthv.bigproject.entity.component.graphic.FeaturedRendererComponent;
import javafx.scene.effect.*;
import javafx.scene.shape.Rectangle;

import static com.ethnicthv.bigproject.client.GameManager.grid;

public class Player extends Entity implements SealedPlayer{

    PlayerData playerData = new PlayerData();

    public DropShadow effect;

    public Player() {
        this.setType(EntityType.PLAYER);
    }

    public Player init() {
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
        this.setLocalAnchorFromCenter();
        this.getViewComponent().addChild(new Rectangle(16, 16));
        this.getViewComponent().setVisible(false);
        this.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.circle(8)));
        this.getTransformComponent().setScaleOrigin(this.getBoundingBoxComponent().getCenterLocal());
        this.getTransformComponent().setRotationOrigin(this.getBoundingBoxComponent().getCenterLocal());
        this.setType(EntityType.PLAYER);
        this.addComponent(new CustomCellMoveComponent(GameManager.OFFSETX, GameManager.OFFSETY, grid.gridsize, grid.gridsize, 300));
        this.addComponent(new CustomAStarMoveComponent(grid.pfg));

        AnimatedGraphicComponent agc = new AnimatedGraphicComponent(new AnimatedTexture(AnimatedChannelProvider.INSTANCE.PLAYER_IDLE) );
        effect = new DropShadow();
        agc.addEffect(effect, 0);

        this.addComponent(agc.setZIndex(4)
                .setOffsetX(-8)
                .setOffsetY(-22)
                .addChannel("walk", AnimatedChannelProvider.INSTANCE.PLAYER_WALK));
        this.addComponent(new CollidableComponent(true));
        this.addComponent(new PlayerControlerComponent());
        this.addComponent(new FeaturedRendererComponent());
        this.setZIndex(1);
        this.setPosition( GameManager.OFFSETX + grid.gridsize * 2, GameManager.OFFSETY + grid.gridsize * 2);

        //this.getBoundingBoxComponent().addHitBox();

        return this;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public Entity toEntity() {
        return this;
    }

    @Override
    public PlayerControlerComponent getPCC() {
        return this.getComponent(PlayerControlerComponent.class);
    }

    public AnimatedGraphicComponent getGraphic() {
        return this.getComponent(AnimatedGraphicComponent.class);
    }
}
