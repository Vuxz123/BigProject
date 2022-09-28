package com.ethnicthv.bigproject.entity;

public enum EntityType {
    PLAYER, WALL, NULL, ENTITY, BOM, PARTICLE;

    EntityType ins = this;

    static {
        PLAYER.ins = ENTITY;
    }
}
