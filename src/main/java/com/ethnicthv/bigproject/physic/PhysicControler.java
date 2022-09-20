package com.ethnicthv.bigproject.physic;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;
import com.ethnicthv.bigproject.physic.collision.RocketWallCollision;

import java.util.*;

public class PhysicControler {
    private Map<String,CollisionHandler> handlers = new HashMap<>();
    private Set<String> checked = new HashSet<>();

    public static final PhysicControler INSTACNE = new PhysicControler();

    private PhysicControler(){
    }

    public void add(String name, CollisionHandler handler) {
        handlers.put(name,handler);
    }

    @Deprecated
    public void setup(){
        handlers.forEach((s, handler) -> {
            FXGL.getPhysicsWorld().addCollisionHandler(handler);
            checked.add(s);
        });
    }

}
