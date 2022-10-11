package com.ethnicthv.bigproject.entity.boom;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.ethnicthv.bigproject.asset.Config;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.play;

public class HBoom extends AbstractBoom {
    @Override
    public Runnable getBoomFunc(int centerX, int centerY) {
        return () -> {
            int x = centerX;
            int l = x + 9;
            SpawnData data = new SpawnData(centerX, centerY);
            data.put("dir", new Point2D(-1, 0));
            FXGL.getGameWorld().spawn("f", data);
            data = new SpawnData(centerX, centerY);
            data.put("dir", new Point2D(1, 0));
            FXGL.getGameWorld().spawn("f", data);
            //why i put here
            play(Config.Asset.SOUNG_EXPLOSION);
        };
    }

    @Override
    public SafeGrid.CellUnSafeFunction getCellFunc() {
        return (cell, centerX, centerY) -> {
            int x = centerX - 4;
            int y = centerY - 1;
            int l = x + 9;
            int l2 = y + 3;
            for (; x < l; x++) {
                GameManager.grid.pfg.setUnSafe(x, y, true);
                GameManager.grid.pfg.setUnSafe(x, y + 1, true);
                GameManager.grid.pfg.setUnSafe(x, y + 2, true);
            }
        };
    }
}
