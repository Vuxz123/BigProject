package com.ethnicthv.bigproject.entity;

public enum EntityType {
    PLAYER, BLOCK, NULL, ENTITY;

    EntityType ins = this;

    static {
        PLAYER.ins = ENTITY;
    }
}
