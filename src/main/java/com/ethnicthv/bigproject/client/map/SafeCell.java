package com.ethnicthv.bigproject.client.map;

import com.almasb.fxgl.core.collection.grid.Cell;
import com.ethnicthv.bigproject.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SafeCell extends Cell {
    public static List<Pair<SafeCell, Double>> markedcell = new ArrayList<>();

    private SafeCell parent;
    private SafeCellState state;

    private int gCost;

    private int hCost;

    public SafeCell(int x, int y, SafeCellState state) {
        super(x, y);
        this.state = state;
    }

    public final boolean isSafe() {
        return state.isSafe();
    }

    public final boolean isNotSafe() {
        return state.isNotSafe();
    }

    public final boolean isWalkable() {
        return state.isWalkable();
    }

    public SafeCell getParent() {
        return parent;
    }

    public void setParent(SafeCell parent) {
        this.parent = parent;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public void setState(SafeCellState state) {
        this.state = state;
        if (state == SafeCellState.SAFE || state == SafeCellState.NOTSAFE) {
            markedcell.add(new Pair<SafeCell, Double>(this, 1d));
        }
    }

    public SafeCellState getState() {
        return state;
    }

    public final int getFCost() {
        return gCost + hCost;
    }

    @Override
    public String toString() {
        return "SafeCell[x=" + getX() + ",y=" + getY() + "," + state + "]";
    }
}
