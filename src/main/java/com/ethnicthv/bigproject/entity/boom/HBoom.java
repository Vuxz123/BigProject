package com.ethnicthv.bigproject.entity.boom;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import javafx.geometry.Point2D;

public class HBoom extends AbstractBoom {
    @Override
    public Runnable getBoomFunc(int centerX, int centerY) {
        return () -> {
            int x = centerX;
            int l = x + 9;
            for (int i = 0; i < 4; i++) {
                SpawnData data = new SpawnData(centerX,centerY);
                data.put("dir", new Point2D(-1,0));
                FXGL.getGameWorld().spawn("f", data);
                data = new SpawnData(centerX,centerY);
                data.put("dir", new Point2D(1,0));
                FXGL.getGameWorld().spawn("f", data);
            }
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
