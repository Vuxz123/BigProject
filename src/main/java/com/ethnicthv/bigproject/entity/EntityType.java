package com.ethnicthv.bigproject.entity;

public enum EntityType {
    PLAYER, WALL, NULL, ENTITY, BOM, PARTICLE, SHIELD, ITEMENTITY, BREAKABLE_BLOCK;

    static {
        PLAYER.ins = ENTITY;
    }

    EntityType ins = this;
}
