package com.ethnicthv.bigproject.item;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.PickupableComponent;
import javafx.geometry.Point2D;

public class ItemEntityFactory implements EntityFactory {

    public static void spawnItem(Item item, Point2D pos) {
        SpawnData data = new SpawnData(pos);
        data.put("item", item);
        FXGL.getGameWorld().spawn("ie", data);
    }

    @Spawns("ie, itementity")
    public Entity itemEntity(SpawnData data) {
        return FXGL.entityBuilder(data)
                .zIndex(5)
                .viewWithBBox(((Item) data.get("item")).getTexture())
                .type(EntityType.ITEMENTITY)
                .with(new PickupableComponent(data.get("item")))
                .collidable()
                .build();
    }
}
