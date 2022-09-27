package com.ethnicthv.bigproject.client.map;

public enum SafeCellState {
    NULL, SAFE, NOTSAFE, NOT_WALKABLE;

    public boolean isSafe() {
        return this == SAFE;
    }

    public boolean isNotSafe() {
        return this == NOTSAFE;
    }

    public boolean isWalkable() {
        return this != NOT_WALKABLE;
    }

    public boolean isNotWalkable() {
        return this == NOT_WALKABLE;
    }
}
