package com.ethnicthv.bigproject.physic;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhysicControler {
    public static final PhysicControler INSTACNE = new PhysicControler();
    private final Map<String, CollisionHandler> handlers = new HashMap<>();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final Set<String> checked = new HashSet<>();

    private PhysicControler() {
    }

    public void add(String name, CollisionHandler handler) {
        handlers.put(name, handler);
    }

    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public void setup() {
        handlers.forEach((s, handler) -> {
            FXGL.getPhysicsWorld().addCollisionHandler(handler);
            checked.add(s);
        });
    }

}
