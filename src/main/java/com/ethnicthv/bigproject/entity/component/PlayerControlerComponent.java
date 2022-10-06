package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import com.ethnicthv.bigproject.entity.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.entity.graphic.FeaturedRendererComponent;
import com.ethnicthv.bigproject.entity.graphic.features.ShieldFeature;
import javafx.animation.Interpolator;
import javafx.geometry.Point2D;
import javafx.util.Duration;

@Required(HealthIntComponent.class)
public class PlayerControlerComponent extends Component {

    //Feature
    public static ShieldFeature SHIELD = new ShieldFeature(Duration.seconds(5));

    //Component
    private HealthIntComponent healthComponent;

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

    public void speedUP() {
        if(!GameManager.getPlayer().getPlayerData().isSpeedUpdelay()) {
            GameManager.getPlayer().getPlayerData().resetSpeedUpdelay();
            FXGL.animationBuilder()
                    .interpolator(Interpolator.SPLINE(0.5,1,2,0.5))
                    .animate(this.entity.getComponent(CustomCellMoveComponent.class).getSpeed())
                    .duration(Duration.seconds(5))

        }
    }

    public void activateShield() {
        if(!GameManager.getPlayer().getPlayerData().isShielddelay()) {
            this.entity.getComponent(FeaturedRendererComponent.class).pushFeature(SHIELD);
        }
    }

    public void placeBoom() {
        if(!GameManager.getPlayer().getPlayerData().isBomDelay()) {
            Point2D pos = GameManager.getPlayer().getPosition();
            SafeCell cell = GameManager.grid.pfg.getCell(pos);
            if (FXGL.getGameWorld().getEntitiesAt(cell.getWorldPosition()).stream().anyMatch(e -> e.getType().toString() == EntityType.BOM.toString())) {
                return;
            }
            GameManager.getPlayer().getPlayerData().resetBomDelay();
            FXGL.getGameWorld().spawn("hb", new SpawnData(pos));
        }
    }
}
