package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.TimerAction;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import com.ethnicthv.bigproject.entity.boom.AbstractBoom;
import javafx.util.Duration;

public class BomComponent extends Component {
    private final Duration duration;
    private final SafeGrid.CellUnSafeFunction function;
    private TimerAction action;
    private Runnable bomfunction;
    private AbstractBoom boom = null;

    public BomComponent(Duration duration, SafeGrid.CellUnSafeFunction function, Runnable bomfunction) {
        this.duration = duration;
        this.function = function;
        this.bomfunction = bomfunction;
    }

    public BomComponent(Duration duration, AbstractBoom boom) {
        this.duration = duration;
        this.function = boom.getCellFunc();
        this.boom = boom;

    }

    @Override
    public void onAdded() {
        super.onAdded();
        if (boom != null)
            this.bomfunction = boom.getBoomFunc(GameManager.grid.getGridX((int) entity.getX()), GameManager.grid.getGridY((int) entity.getY()));
        action = FXGL.getGameTimer().runOnceAfter(bomfunction, duration);
        FXGL.getGameTimer().runOnceAfter(() -> GameManager.grid.pfg.setUnSafe(GameManager.grid.getGridX((int) this.entity.getX()), GameManager.grid.getGridY((int) this.entity.getY()), function), duration.subtract(Duration.seconds(1)));
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        if (action.isExpired()) {
            entity.removeFromWorld();
        }
    }
}
