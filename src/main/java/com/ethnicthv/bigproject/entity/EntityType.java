package com.ethnicthv.bigproject.entity;

public enum EntityType {
    PLAYER, WALL, NULL, ENTITY, BOM, PARTICLE, SHIELD;

    EntityType ins = this;

    static {
        PLAYER.ins = ENTITY;
    }
}
