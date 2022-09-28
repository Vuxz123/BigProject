package com.ethnicthv.bigproject.entity.particle;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileWithAccelerationComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.ethnicthv.bigproject.entity.component.DurationComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ParticleFactory implements EntityFactory {
    @Spawns("f")
    public Entity f(SpawnData data) {
        int x = (int) data.getX();
        int y = (int) data.getY();
        Entity e = FXGL.entityBuilder(data)
                .at(5 + x*16 + 8, 5 + y*16 + 8)
                .view(new Circle(8, Color.RED))
                .with(new ProjectileWithAccelerationComponent(data.get("dir"),400, ((Point2D) data.get("dir")).multiply(-50)))
                .with(new DurationComponent(DurationComponent.Type.MILLISECOND, 250))
                .build();
        return e;
    }
}
