package com.ethnicthv.bigproject.entity.boom;

import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeGrid;

public class HBoom extends AbstractBoom {
    @Override
    public Runnable getBoomFunc() {
        return () -> {

        };
    }

    @Override
    public SafeGrid.CellUnSafeFunction getCellFunc() {
        return (cell, centerX, centerY) -> {
            int x = centerX - 4;
            int l = x + 9;
            for (; x < l; x++) {
                GameManager.grid.pfg.setUnSafe(x, centerY, true);
            }
        };
    }
}
