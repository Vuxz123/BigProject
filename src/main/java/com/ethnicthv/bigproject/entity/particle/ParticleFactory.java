package com.ethnicthv.bigproject.entity.particle;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileWithAccelerationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.DurationComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ParticleFactory implements EntityFactory {
    @Spawns("f")
    public Entity f(SpawnData data) {
        int x = (int) data.getX();
        int y = (int) data.getY();
        Entity e = FXGL.entityBuilder(data)
                .viewWithBBox(new Rectangle(32 + 16, 32 + 16, Color.RED))
                .at(5 + x*16, 5 + y*16 - 16)
                .type(EntityType.PARTICLE)
                .with(new ProjectileWithAccelerationComponent(data.get("dir"),200, ((Point2D) data.get("dir")).multiply(-100)))
                .with(new DurationComponent(DurationComponent.Type.MILLISECOND, 250))
                .collidable()
                .build();
        return e;
    }
}
