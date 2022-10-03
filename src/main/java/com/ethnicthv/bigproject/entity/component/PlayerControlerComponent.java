package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.entity.component.Component;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import com.ethnicthv.bigproject.entity.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.entity.graphic.features.ShieldFeature;
import javafx.util.Duration;

public class PlayerControlerComponent extends Component {

    public static ShieldFeature SHIELD = new ShieldFeature(Duration.seconds(5));

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        if(this.entity.getComponent(CustomCellMoveComponent.class).isMovingLeft()) {
            this.entity.getComponent(AnimatedGraphicComponent.class).mirror(true);
        }
        if(this.entity.getComponent(CustomCellMoveComponent.class).isMovingRight()) {
            this.entity.getComponent(AnimatedGraphicComponent.class).mirror(false);
        }
        if(this.entity.getComponent(CustomCellMoveComponent.class).isMoving()) {
            this.entity.getComponent(AnimatedGraphicComponent.class).playChannel("walk");
        }else {
            this.entity.getComponent(AnimatedGraphicComponent.class).playChannel("idle");
        }
    }
}
