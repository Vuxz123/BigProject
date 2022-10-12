package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.client.map.SafeCellState;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.boom.BlockBoom;
import com.ethnicthv.bigproject.item.ItemEntityFactory;
import com.ethnicthv.bigproject.item.items.CoinItem;
import com.ethnicthv.bigproject.item.items.HealthItem;
import com.ethnicthv.bigproject.item.items.ManaItem;
import com.ethnicthv.bigproject.ui.UIControlor;
import com.ethnicthv.bigproject.util.Pair;
import com.ethnicthv.bigproject.util.Pos;
import com.ethnicthv.bigproject.util.Util;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameMechanicComponent extends Component {

    private LocalTimer timer;

    @Override
    public void onAdded() {
        timer = FXGL.newLocalTimer();
        super.onAdded();
        timer.capture();
        FXGL.run(() -> {
            int x = FXGLMath.random(2, 22);
            int y = FXGLMath.random(2, 14);
            if (GameManager.grid.pfg.get(x, y).isWalkable()
                    && FXGL.getGameWorld().getEntitiesByType(EntityType.ENTITY).size() < GameManager.grid.max_entity
                    && GameManager.grid.pfg.get(x, y).getWorldPosition().distance(GameManager.grid.pfg.get(2, 2).getWorldPosition()) < 150) {
                Util.spawnNPC(x, y);
            }
        }, Duration.seconds(1));
        FXGL.run(() -> {
            var lcell = GameManager.grid.pfg.getWalkableCell();
            var cell = FXGLMath.random(lcell);
            if (cell.isEmpty() || cell.get().getWorldPosition().distance(GameManager.grid.pfg.get(2, 2).getWorldPosition()) < 100) {
                return;
            }
            int i = cell.get().getX() - 1;
            int j = cell.get().getY() - 1;
            for (Pos p : BlockBoom.pair) {
                int x = i + p.getKey();
                int y = j + p.getValue();
                if (GameManager.grid.pfg.get(x, y).getState() != SafeCellState.NOT_WALKABLE) {
                    //System.out.println("" + p.getKey() + " " + p.getValue() + " " + GameManager.grid.pfg.get(x, y).isWalkable() + " " + x + " " + y + " " + GameManager.grid.pfg.get(x, y).getState());
                    FXGL.spawn("block", x * GameManager.grid.gridsize + GameManager.OFFSETX, y * GameManager.grid.gridsize + GameManager.OFFSETY);
                    Point2D v = GameManager.grid.pfg.get(x, y).getWorldPosition().add(8, 8);
                    if (GameManager.getPlayer().getPosition().getX() == v.getX() && GameManager.getPlayer().getPosition().getY() == v.getY()) {
                        GameManager.getPlayer().getPlayerData().dealDamage(100);
                    }
                }
            }

        }, Duration.seconds(20));
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        UIControlor.INSTANCE.onUpdate(tpf);
        GameManager.getPlayer().getPlayerData().onUpdate(tpf);
        List<Pair> removed = new ArrayList<>();
        SafeCell.markedcell.forEach(pair -> {
            if (!pair.getKey().isWalkable()) {
                removed.add(pair);
            }
            if (pair.getValue() <= 0) {
                Util.setBlockChange(pair.getKey().getX(), pair.getKey().getY(), Color.WHITE);
                pair.getKey().setState(SafeCellState.NULL);
                removed.add(pair);
                return;
            }
            pair.setValue(pair.getValue() - tpf);
        });
        removed.forEach(pair -> {
            SafeCell.markedcell.remove(pair);
        });
        if (timer.elapsed(Duration.seconds(20))) {
            int x = FXGLMath.random(2, 22);
            int y = FXGLMath.random(2, 14);
            if (GameManager.grid.pfg.get(x, y).isWalkable()) {
                double r = FXGLMath.random(0, 1);
                if (r <= 0.33) {
                    ItemEntityFactory.spawnItem(new CoinItem(TextureProvider.INSTANCE.EMBER.copy()),
                            GameManager.grid.pfg.get(x, y).getWorldPosition());
                } else if (r <= 0.66) {
                    ItemEntityFactory.spawnItem(new HealthItem(),
                            GameManager.grid.pfg.get(x, y).getWorldPosition());
                } else {
                    ItemEntityFactory.spawnItem(new ManaItem(),
                            GameManager.grid.pfg.get(x, y).getWorldPosition());
                }
            }
            timer.capture();
        }
    }
}
