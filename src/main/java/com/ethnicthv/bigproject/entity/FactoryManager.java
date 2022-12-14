package com.ethnicthv.bigproject.entity;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.EntityFactory;

import java.util.ArrayList;
import java.util.List;

public class FactoryManager {
    public static final FactoryManager INSTANCE = new FactoryManager();
    private final List<EntityFactory> entityFactories = new ArrayList<>();

    private FactoryManager() {
    }

    public void addFactory(EntityFactory factory) {
        entityFactories.add(factory);
        FXGL.getGameWorld().addEntityFactory(factory);
    }

    @Deprecated
    public void addFactoryX(EntityFactory factory) {
        entityFactories.add(factory);
    }

    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public void setup() {
        entityFactories.forEach((entityFactory) -> FXGL.getGameWorld().addEntityFactory(entityFactory));
    }
}
