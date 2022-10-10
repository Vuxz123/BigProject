package com.ethnicthv.bigproject.entity.entities;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.BlockBoomComponent;
import com.ethnicthv.bigproject.entity.component.graphic.GraphicComponent;

public class Block extends Entity {

    public Block() {
        this.getViewComponent().addChild(TextureProvider.INSTANCE.BLOCK.copy());
        this.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(16, 16)));
        this.setType(EntityType.BREAKABLE_BLOCK);
        this.addComponent(new CollidableComponent(true));
        this.addComponent(new BlockBoomComponent());
    }

}
