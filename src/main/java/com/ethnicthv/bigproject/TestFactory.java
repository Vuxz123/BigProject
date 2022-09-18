package com.ethnicthv.bigproject;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import com.ethnicthv.bigproject.asset.ParticleProvider;
import com.ethnicthv.bigproject.asset.TextureProvider;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.RotateEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import static com.almasb.fxgl.core.math.FXGLMath.random;

public class TestFactory implements EntityFactory {

    @Spawns("Projectile")
    public Entity spawnProjectile(SpawnData data){
        Point2D current = new Point2D(200,200);
        Point2D mouse = FXGL.getInput().getMousePositionWorld();
        Point2D dir = current.subtract(mouse);
        Texture a = TextureProvider.INSTANCE.ROCKET.copy();
        Rotate rotate = new Rotate();
        rotate.setPivotX(0);
        rotate.setPivotY(10);
        rotate.setAngle(dir.angle(new Point2D(-1,0)));
        a.getTransforms().add(rotate);

        Entity res = FXGL.entityBuilder(data)
                .type(Type.ROCKET)
                .view(a)
                .bbox(new HitBox(a.localToParent(new Point2D(90,10)), BoundingShape.box(10,10)))
                .at(current)
                .with(new ProjectileComponent(dir, -400).allowRotation(false))
                .with(new OffscreenCleanComponent())
                .with(new ParticleComponent(ParticleProvider.INSTANCE.getEmberEmitter(dir,current)))
                .with(new CollidableComponent(true))
                .build();
        //res.setLocalAnchor(new Point2D(0,10));
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

    @Spawns("Explosion")
    public Entity spawnExplosion(SpawnData data){
        return FXGL.entityBuilder(data)
                .at(data.get("x"),data.get("y"))
                .with(new ParticleComponent(ParticleEmitters.newExplosionEmitter(10)))
                .build();
    }
}
