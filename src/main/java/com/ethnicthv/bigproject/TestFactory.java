package com.ethnicthv.bigproject;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.BoundsAccessor;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.events.Event;

public class TestFactory implements EntityFactory {

    @Spawns("Projectile")
    public Entity spawnProjectile(SpawnData data){
        Point2D current = new Point2D(10,10);
        Point2D mouse = FXGL.getInput().getMousePositionWorld();
        ParticleEmitter a = ParticleEmitters.newFireEmitter();
        a.setBlendMode(BlendMode.SRC_OVER);
        a.setEmissionRate(0.5);
        a.setNumParticles(10);
        a.setMaxEmissions(100);
        Entity res = FXGL.entityBuilder(data)
                .type(Type.ROCKET)
                .view(new Rectangle(100,10))
                .bbox(BoundingShape.box(100,10))
                .at(current)
                .with(new ProjectileComponent(current.subtract(mouse), -400))
                .with(new OffscreenCleanComponent())
                .with(new ParticleComponent(a))
                .with(new CollidableComponent(true))
                .build();
        return res;
    }

    @Spawns("Block")
    public Entity spawnBlock(SpawnData data){
        Point2D mouse = FXGL.getInput().getMousePositionWorld();
        Node a = new Rectangle(30,30);
        Entity res = FXGL.entityBuilder(data)
                .type(Type.BLOCK)
                .viewWithBBox(a)
                .at(mouse.add(new Point2D(-15,-15)))
                .with(new CollidableComponent(true))
                .build();
        return res;
    }
}
