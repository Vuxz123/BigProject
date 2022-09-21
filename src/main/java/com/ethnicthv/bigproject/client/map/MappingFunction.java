package com.ethnicthv.bigproject.client.map;

import com.almasb.fxgl.pathfinding.CellState;
import com.ethnicthv.bigproject.entity.EntityType;
import javafx.scene.control.Cell;

public class MappingFunction {

    public static CellState apply(Object t){
        return t == EntityType.BLOCK ? CellState.NOT_WALKABLE : CellState.WALKABLE;
    }

}
