package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.time.TimerAction;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.graphic.AnimatedGraphicComponent;
import com.ethnicthv.bigproject.entity.component.graphic.FeaturedRendererComponent;
import com.ethnicthv.bigproject.entity.component.graphic.features.ShieldFeature;
import com.ethnicthv.bigproject.entity.component.graphic.features.SpeedUpFeature;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import javafx.animation.Interpolator;
import javafx.geometry.Point2D;
import javafx.scene.effect.MotionBlur;
import javafx.util.Duration;

public class PlayerControlerComponent extends Component {
    TimerAction action = null;
    //Feature
    public static ShieldFeature SHIELD = new ShieldFeature(Duration.seconds(5));

    public static SpeedUpFeature SPEED = new SpeedUpFeature();

    private boolean invincible = false;

    //Component

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

    public void blockWay() {
        Point2D pos = GameManager.getPlayer().getPosition();
        SafeCell cell = GameManager.grid.pfg.getCell(pos);
        if (FXGL.getGameWorld().getEntitiesAt(cell.getWorldPosition()).stream().anyMatch(e -> e.getType().toString() == EntityType.BOM.toString())) {
            return;
        }
        GameManager.getPlayer().getPlayerData().resetBomDelay();
        FXGL.getGameWorld().spawn("bb", new SpawnData(pos));
    }

    public void speedUP() {
        if(!GameManager.getPlayer().getPlayerData().isSpeedUpdelay()) {
            GameManager.getPlayer().getPlayerData().resetSpeedUpdelay();
            this.entity.getComponent(FeaturedRendererComponent.class).pushFeature(SPEED);
            FXGL.animationBuilder()
                    .interpolator(Interpolator.SPLINE(0.5,1,0.75,0.5))
                    .onFinished(() -> {
                        FXGL.animationBuilder()
                                .interpolator(Interpolator.SPLINE(0.5,1,0.75,0.5))
                                .onFinished(() -> {
                                    this.entity.getComponent(FeaturedRendererComponent.class).popFeature(SPEED);
                                })
                                .duration(Duration.seconds(2))
                                .autoReverse(true)
                                .animate(this.entity.getComponent(CustomCellMoveComponent.class).getSpeed())
                                .from(GameManager.player.getComponent(CustomCellMoveComponent.class).getSpeed().doubleValue())
                                .to(GameManager.player.getComponent(CustomCellMoveComponent.class).getSpeed().doubleValue() - 200)
                                .buildAndPlay();
                    })
                    .duration(Duration.seconds(5))
                    .autoReverse(true)
                    .animate(this.entity.getComponent(CustomCellMoveComponent.class).getSpeed())
                    .from(GameManager.player.getComponent(CustomCellMoveComponent.class).getSpeed().doubleValue())
                    .to(GameManager.player.getComponent(CustomCellMoveComponent.class).getSpeed().doubleValue() + 200)
                    .buildAndPlay();

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

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }
}
