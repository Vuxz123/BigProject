package com.ethnicthv.bigproject.entity.particle;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.DurationComponent;

public class ParticleFactory implements EntityFactory {
    @Spawns("f")
    public Entity f(SpawnData data) {
        int x = (int) data.getX();
        int y = (int) data.getY();
        Entity e = FXGL.entityBuilder(data)
                .bbox(BoundingShape.circle(16))
                .view(TextureProvider.INSTANCE.BLAST.copy())
                .anchorFromCenter()
                .at(5 + x * 16 + 8, 5 + y * 16 - 8)
                .type(EntityType.PARTICLE)
                .with(new ProjectileComponent(data.get("dir"), data.get("spe")))
                .with(new DurationComponent(DurationComponent.Type.MILLISECOND, data.get("du")))
                .collidable()
                .build();
        return e;
    }
}
