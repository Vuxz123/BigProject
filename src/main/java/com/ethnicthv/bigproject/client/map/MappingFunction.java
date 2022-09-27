package com.ethnicthv.bigproject.client.map;

import com.ethnicthv.bigproject.entity.EntityType;

public class MappingFunction {

    public static SafeCellState apply(Object t) {
        if (t instanceof EntityType) {
            return t == EntityType.BLOCK ? SafeCellState.NOT_WALKABLE : SafeCellState.NULL;
        }
        return SafeCellState.NULL;
    }

}
