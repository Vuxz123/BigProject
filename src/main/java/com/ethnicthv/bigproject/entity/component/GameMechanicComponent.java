package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.entity.component.Component;
import com.ethnicthv.bigproject.util.Pair;
import com.ethnicthv.bigproject.util.Util;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.client.map.SafeCellState;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameMechanicComponent extends Component {
    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        List<Pair> removed = new ArrayList<>();
        SafeCell.markedcell.forEach(pair -> {
            if(pair.getValue() <= 0){
                Util.setBlockChange(pair.getKey().getX(),pair.getKey().getY(), Color.WHITE);
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
