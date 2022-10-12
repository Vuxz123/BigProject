package com.ethnicthv.bigproject.entity.boom;

import com.almasb.fxgl.dsl.FXGL;
import com.ethnicthv.bigproject.asset.Config;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCellState;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import com.ethnicthv.bigproject.util.Pos;
import javafx.geometry.Point2D;

public class BlockBoom extends AbstractBoom {

    public static Pos[] pair = {
            new Pos(0, 0), new Pos(0, 1), new Pos(0, 2),
            new Pos(1, 0), new Pos(1, 1), new Pos(1, 2),
            new Pos(2, 0), new Pos(2, 1), new Pos(2, 2)
    };

    @Override
    public Runnable getBoomFunc(int centerX, int centerY) {
        return () -> {
            int i = centerX - 1;
            int j = centerY - 1;
            for (Pos p : pair) {
                int x = i + p.getKey();
                int y = j + p.getValue();
                if (GameManager.grid.pfg.get(x, y).getState() != SafeCellState.NOT_WALKABLE) {
                    //System.out.println("" + p.getKey() + " " + p.getValue() + " " + GameManager.grid.pfg.get(x, y).isWalkable() + " " + x + " " + y + " " + GameManager.grid.pfg.get(x, y).getState());
                    FXGL.spawn("block", x * GameManager.grid.gridsize + GameManager.OFFSETX, y * GameManager.grid.gridsize + GameManager.OFFSETY);
                    Point2D v = GameManager.grid.pfg.get(x, y).getWorldPosition().add(8,8);
                    if(GameManager.getPlayer().getPosition().getX() == v.getX() && GameManager.getPlayer().getPosition().getY() == v.getY()) {
                        GameManager.getPlayer().getPlayerData().dealDamage(100);
                    }
                }
            }
            FXGL.play(Config.Asset.SOUNG_EXPLOSION);
        };
    }

    @Override
    public SafeGrid.CellUnSafeFunction getCellFunc() {
        return (cell, centerX, centerY) -> {
            int x = centerX - 1;
            int y = centerY - 1;
            int l = x + 3;
            int l2 = y + 3;
            for (; x < l; x++) {
                GameManager.grid.pfg.setUnSafe(x, y, true);
                GameManager.grid.pfg.setUnSafe(x, y + 1, true);
                GameManager.grid.pfg.setUnSafe(x, y + 2, true);
            }
        };
    }
}
