package com.ethnicthv.bigproject.entity.boom;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.ethnicthv.bigproject.asset.AnimatedChannelProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.BomComponent;
import com.ethnicthv.bigproject.entity.component.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.input.InputControler;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BomFactory implements EntityFactory {

    @Spawns("horibom,hb")
    public Entity hb(SpawnData data) {
        int x = GameManager.grid.getGridX((int) data.getX());
        int y = GameManager.grid.getGridY((int) data.getY());
        Entity e = FXGL.entityBuilder(data)
                .at(GameManager.OFFSETX + x * GameManager.grid.gridsize, GameManager.OFFSETY + y * GameManager.grid.gridsize)
                .type(EntityType.BOM)
                .with(new BomComponent(Duration.seconds(5), new HBoom()))
                .with(new AnimatedGraphicComponent(new AnimatedTexture(AnimatedChannelProvider.INSTANCE.BOOM)).setOffsetY(-16))
                .zIndex(11)
                .build();
        return e;
    }

    @Spawns("blockbom,bb")
    public Entity bb(SpawnData data) {
        int x = GameManager.grid.getGridX((int) data.getX());
        int y = GameManager.grid.getGridY((int) data.getY());
        Entity e = FXGL.entityBuilder(data)
                .at(GameManager.OFFSETX + x * GameManager.grid.gridsize, GameManager.OFFSETY + y * GameManager.grid.gridsize)
                .type(EntityType.BOM)
                .with(new BomComponent(Duration.seconds(3), new BlockBoom()))
                .with(new AnimatedGraphicComponent(new AnimatedTexture(AnimatedChannelProvider.INSTANCE.BOOM)).setOffsetY(-16))
                .zIndex(11)
                .build();

        return e;
    }

}
