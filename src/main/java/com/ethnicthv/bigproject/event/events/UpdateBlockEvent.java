package com.ethnicthv.bigproject.event.events;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.paint.Color;

public class UpdateBlockEvent extends Event {
    public static final EventType<UpdateBlockEvent> UB = new EventType<>("UPDATE BLOCK");

    private final int cellX;
    private final int cellY;
    private final Color color;

    public UpdateBlockEvent(int cellX, int cellY, Color color) {
        super(UB);
        this.cellX = cellX;
        this.cellY = cellY;
        this.color = color;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public Color getColor() {
        return color;
    }
}
