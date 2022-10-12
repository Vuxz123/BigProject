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
import com.ethnicthv.bigproject.entity.component.PlayerControlerComponent;
import com.ethnicthv.bigproject.entity.component.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.entity.component.graphic.FeaturedRendererComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import javafx.scene.effect.DropShadow;

import static com.ethnicthv.bigproject.client.GameManager.grid;

public class Player extends Entity implements SealedPlayer {

    public DropShadow effect;
    final PlayerData playerData = new PlayerData();

    public Player() {
        this.setType(EntityType.PLAYER);
    }

    public Player init() {
        this.setLocalAnchorFromCenter();
        this.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.circle(8)));
        this.getBoundingBoxComponent().getTransform().positionOriginXProperty().set(8);
        this.getBoundingBoxComponent().getTransform().positionOriginYProperty().set(8);
        this.getTransformComponent().setScaleOrigin(this.getBoundingBoxComponent().getCenterLocal());
        this.getTransformComponent().setRotationOrigin(this.getBoundingBoxComponent().getCenterLocal());
        this.setType(EntityType.PLAYER);
        this.addComponent(new CustomCellMoveComponent(GameManager.OFFSETX, GameManager.OFFSETY, grid.gridsize, grid.gridsize, 300));
        this.addComponent(new CustomAStarMoveComponent(grid.pfg));

        AnimatedGraphicComponent agc = new AnimatedGraphicComponent(new AnimatedTexture(AnimatedChannelProvider.INSTANCE.PLAYER_IDLE));
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
        this.setPosition(GameManager.OFFSETX + grid.gridsize * 2, GameManager.OFFSETY + grid.gridsize * 2);

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
