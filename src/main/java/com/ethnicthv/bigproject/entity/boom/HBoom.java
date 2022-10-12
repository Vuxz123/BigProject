package com.ethnicthv.bigproject.entity.boom;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.ethnicthv.bigproject.asset.Config;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import com.ethnicthv.bigproject.util.Pos;
import javafx.geometry.Point2D;

public class HBoom extends AbstractBoom {

    final Pos[] pair = {
            new Pos(-3, 0), new Pos(-3, 1), new Pos(-3, 2),
            new Pos(-2, 0), new Pos(-2, 1), new Pos(-2, 2),
            new Pos(-1, 0), new Pos(-1, 1), new Pos(-4, 2),
            new Pos(0, -3), new Pos(0, -2), new Pos(0, -1), new Pos(0, 0), new Pos(0, 1), new Pos(0, 2), new Pos(0, 3), new Pos(0, 4), new Pos(0, 5),
            new Pos(1, -3), new Pos(1, -2), new Pos(1, -1), new Pos(1, 0), new Pos(1, 1), new Pos(1, 2), new Pos(1, 3), new Pos(1, 4), new Pos(1, 5),
            new Pos(2, -3), new Pos(2, -2), new Pos(2, -1), new Pos(2, 0), new Pos(2, 1), new Pos(2, 2), new Pos(2, 3), new Pos(2, 4), new Pos(2, 5),
            new Pos(3, 0), new Pos(3, 1), new Pos(3, 2),
            new Pos(4, 0), new Pos(4, 1), new Pos(4, 2),
            new Pos(5, 0), new Pos(5, 1), new Pos(5, 2)
    };

    @Override
    public Runnable getBoomFunc(int centerX, int centerY) {
        return () -> {
            int l = centerX + 9;
            SpawnData data = new SpawnData(centerX - 1, centerY);
            data.put("spe", GameManager.getPlayer().getPlayerData().getBoomSpe());
            data.put("du", GameManager.getPlayer().getPlayerData().getBoomDuration());
            data.put("dir", new Point2D(-1, 0));
            FXGL.getGameWorld().spawn("f", data);
            data.put("dir", new Point2D(1, 0));
            FXGL.getGameWorld().spawn("f", data);
            data.put("dir", new Point2D(0, -1));
            FXGL.getGameWorld().spawn("f", data);
            data.put("dir", new Point2D(0, 1));
            FXGL.getGameWorld().spawn("f", data);
            FXGL.play(Config.Asset.SOUNG_EXPLOSION);
        };
    }

    @Override
    public SafeGrid.CellUnSafeFunction getCellFunc() {
        return (cell, centerX, centerY) -> {
            int x = centerX - 1;
            int y = centerY - 1;
            for (Pos p : pair) {
                int i = x + p.getKey();
                int j = y + p.getValue();
                //noinspection deprecation
                GameManager.grid.pfg.setUnSafe(i, j, true);
            }
        };
    }
}
