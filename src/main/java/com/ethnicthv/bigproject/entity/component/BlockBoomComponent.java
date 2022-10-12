package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.entity.component.Component;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCellState;

public class BlockBoomComponent extends Component {

    @Override
    public void onAdded() {
        super.onAdded();
        GameManager.grid.pfg.getCell(this.entity).setState(SafeCellState.NOT_WALKABLE);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        if (GameManager.grid.pfg.getCell(this.entity).isWalkable())
            GameManager.grid.pfg.getCell(this.entity).setState(SafeCellState.NOT_WALKABLE);
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        GameManager.grid.pfg.getCell(this.entity).setState(SafeCellState.NULL);
    }
}
