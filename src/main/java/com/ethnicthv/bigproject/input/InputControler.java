package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.core.collection.grid.Grid;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import org.jetbrains.annotations.Nullable;

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

        FXGL.getInput().addAction(new UserAction("SET UNSAFE") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                Point2D mouse = FXGL.getInput().getMousePositionWorld();
                //SafeGrid g = GameManager.grid.pfg;
                FXGL.runOnce(() -> GameManager.grid.pfg.setUnSafe(GameManager.grid.getGridX((int) mouse.getX()), GameManager.grid.getGridY((int) mouse.getY()), (cell, centerX, centerY) -> {
                    int x = centerX - 4;
                    int l = x + 9;
                    for (; x < l; x++) {
                        GameManager.grid.pfg.setUnSafe(x, centerY, true);
                    }
                }), Duration.ZERO);
            }
        }, MouseButton.SECONDARY);

        FXGL.getInput().addAction(new UserAction("CHECK STATE") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                Point2D mouse = FXGL.getInput().getMousePositionWorld();
            }
        }, KeyCode.K);
    }
}
