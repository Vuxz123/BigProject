package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
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
                GameManager.player.getComponent(CustomAStarMoveComponent.class).moveToCell(GameManager.grid.pfg.getCell(mouse));
            }
        }, MouseButton.PRIMARY);

        FXGL.getInput().addAction(new UserAction("CHECK STATE") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                Point2D mouse = FXGL.getInput().getMousePositionWorld();
            }
        }, KeyCode.K);

        FXGL.getInput().addAction(new UserAction("PLACE BOOM") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                Point2D pos = GameManager.player.getPosition();
                SafeCell cell = GameManager.grid.pfg.getCell(pos);
                if (FXGL.getGameWorld().getEntitiesAt(cell.getWorldPosition()).stream().anyMatch(e -> e.getType().toString() == EntityType.BOM.toString())) {
                    return;
                }
                FXGL.getGameWorld().spawn("hb", new SpawnData(pos));
            }
        }, KeyCode.SPACE);
    }
}
