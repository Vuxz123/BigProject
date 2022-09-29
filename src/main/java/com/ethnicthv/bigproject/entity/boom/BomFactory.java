package com.ethnicthv.bigproject.entity.boom;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.BomComponent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BomFactory implements EntityFactory {

    @Spawns("horibom,hb")
    public Entity hb(SpawnData data) {
        int x = GameManager.grid.getGridX((int) data.getX());
        int y = GameManager.grid.getGridY((int) data.getY());
        Entity e = FXGL.entityBuilder(data)
                .at(GameManager.OFFSETX + x * GameManager.grid.gridsize, GameManager.OFFSETY + y * GameManager.grid.gridsize)
                .view(new Rectangle(GameManager.grid.gridsize, GameManager.grid.gridsize))
                .type(EntityType.BOM)
                .with(new BomComponent(Duration.seconds(5), new HBoom()))
                .zIndex(11)
                .build();
        return e;
    }

}
