package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.client.map.SafeCellState;
import com.ethnicthv.bigproject.ui.UIControlor;
import com.ethnicthv.bigproject.util.Pair;
import com.ethnicthv.bigproject.util.Util;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameMechanicComponent extends Component {

    @Override
    public void onAdded() {
        super.onAdded();
//        FXGL.run(() -> {
//            int x = FXGLMath.random(2, 22);
//            int y = FXGLMath.random(2, 14);
//            if(GameManager.grid.pfg.get(x,y).isWalkable()) Util.spawnNPC(x,y);
//        }, Duration.seconds(1));
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        UIControlor.INSTANCE.onUpdate(tpf);
        GameManager.getPlayer().getPlayerData().onUpdate(tpf);
        List<Pair> removed = new ArrayList<>();
        SafeCell.markedcell.forEach(pair -> {
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
    }
}
