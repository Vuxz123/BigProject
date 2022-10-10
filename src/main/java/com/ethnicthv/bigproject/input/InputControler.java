package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.item.ItemEntityFactory;
import com.ethnicthv.bigproject.item.items.CoinItem;
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
                GameManager.getPlayer().toEntity().
                        getComponent(CustomAStarMoveComponent.class).moveToCell(GameManager.grid.pfg.getCell(mouse));
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

        FXGL.getInput().addAction(new UserAction("Test") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                ItemEntityFactory.spawnItem(new CoinItem(TextureProvider.INSTANCE.EMBER.copy()),
                        GameManager.grid.pfg.getCell(FXGL.getInput().getMousePositionWorld()).getWorldPosition());
            }
        }, MouseButton.SECONDARY);

        FXGL.getInput().addAction(new UserAction("Test2") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().speedUP();
            }
        }, KeyCode.L);

        FXGL.getInput().addAction(new UserAction("Test3") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().blockWay();
            }
        }, KeyCode.M);
    }
}
