package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.ui.UI;
import com.ethnicthv.bigproject.asset.Config;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
//import com.ethnicthv.bigproject.client.GameMenu;
import com.ethnicthv.bigproject.client.controller.SaveDataController;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.DurationComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.item.ItemEntityFactory;
import com.ethnicthv.bigproject.item.items.CoinItem;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.time.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.ethnicthv.bigproject.input.ControlOption.*;

public class InputControler {

    private ControlOption c;

    public static final InputControler INSTANCE = new InputControler();
    private int maxBom = 4;
    private int bombsPlaced = 0;
    public int getBombPlaced() {
        return bombsPlaced;
    }
    private InputControler() {
    }

    public void getControlOption(ControlOption c) {
        this.c = c;
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
            FXGL.getInput().addAction(new UserAction("END GAME") {
                @Override
                protected void onAction() {
                    SaveDataController controller = new SaveDataController();

                    // 2. place fxml file in "assets/ui" and load it
                    UI fxmlUI = getAssetLoader().loadUI("SaveData.fxml", controller);


                    // 4. add UI to game scene
                    getGameScene().addUI(fxmlUI);
                }
            }, KeyCode.Q);
        //if (c.equals(MOUSE)) {

        //}
       // if (c.equals(KEYBOARD)) {

            FXGL.getInput().addAction(new UserAction("GO RIGHT") {
                @Override
                protected void onAction() {
                    super.onAction();
//                if (GameManager.player.getX() < GameManager.WIDTH && GameManager.player.getX() > 0) {
//                    GameManager.player.translateX(3);
//                }

                    GameManager.player.getComponent(CustomAStarMoveComponent.class).moveToRightCell();
                }
            }, KeyCode.D);
            FXGL.getInput().addAction(new UserAction("GO LEFT") {
                @Override
                protected void onAction() {
                    super.onAction();
                    GameManager.player.getComponent(CustomAStarMoveComponent.class).moveToLeftCell();
                }
            }, KeyCode.A);

            FXGL.getInput().addAction(new UserAction("GO UP") {
                @Override
                protected void onAction() {
                    super.onAction();
                    GameManager.player.getComponent(CustomAStarMoveComponent.class).moveToUpCell();
                }
            }, KeyCode.W);
            FXGL.getInput().addAction(new UserAction("GO DOWN") {
                @Override
                protected void onAction() {
                    super.onAction();
                    GameManager.player.getComponent(CustomAStarMoveComponent.class).moveToDownCell();
                }
            }, KeyCode.S);
       // }

        FXGL.getInput().addAction(new UserAction("SHIELD") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().activateShield();
            }
        }, KeyCode.K);
        FXGL.getInput().addAction(new UserAction("GO MENU") {
            @Override
            protected void onActionEnd() {
                super.onActionEnd();

            }
        }, KeyCode.ESCAPE);
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
