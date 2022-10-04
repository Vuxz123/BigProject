package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.PlayerControlerComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.graphic.FeaturedRendererComponent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class InputControler {
    public static final InputControler INSTANCE = new InputControler();

    private InputControler() {
    }

    @Deprecated
    public void setup() {
        FXGL.getInput().addAction(new UserAction("MOVE") {
            @Override
            protected void onAction() {
                super.onAction();
                Point2D mouse = FXGL.getInput().getMousePositionWorld();
                GameManager.getPlayer().toEntity().getComponent(CustomAStarMoveComponent.class).moveToCell(GameManager.grid.pfg.getCell(mouse));
            }
        }, MouseButton.PRIMARY);

        FXGL.getInput().addAction(new UserAction("SHIELD") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().activateShield();
            }
        }, KeyCode.K);

        FXGL.getInput().addAction(new UserAction("PLACE BOOM") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().placeBoom();
            }
        }, KeyCode.SPACE);
    }
}
