package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.almasb.fxgl.time.TimerAction;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import com.ethnicthv.bigproject.entity.boom.AbstractBoom;
import javafx.util.Duration;

public class BomComponent extends Component {
    private TimerAction action;
    private final Duration duration;
    private final SafeGrid.CellUnSafeFunction function;
    private final Runnable bomfunction;

    public BomComponent(Duration duration, SafeGrid.CellUnSafeFunction function, Runnable bomfunction) {
        this.duration = duration;
        this.function = function;
        this.bomfunction = bomfunction;
    }

    public BomComponent(Duration duration, AbstractBoom boom) {
        this.duration = duration;
        this.function = boom.getCellFunc();
        this.bomfunction = boom.getBoomFunc();
    }

    @Override
    public void onAdded() {
        super.onAdded();
        FXGL.runOnce(()->{
            GameManager.grid.pfg.setUnSafe(GameManager.grid.getGridX((int) this.entity.getX()), GameManager.grid.getGridY((int) this.entity.getY()), function);
        }, duration.subtract(Duration.seconds(1)));
        action = FXGL.runOnce(bomfunction, duration);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        if(action.isExpired()){
            entity.removeFromWorld();
        }
    }
}
