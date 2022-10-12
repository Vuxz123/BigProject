package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.ui.UI;
import com.ethnicthv.bigproject.asset.Config;
import com.ethnicthv.bigproject.client.GameManager;
//import com.ethnicthv.bigproject.client.GameMenu;
import com.ethnicthv.bigproject.client.controller.SaveDataController;
import com.ethnicthv.bigproject.client.map.SafeCell;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.DurationComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
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
            FXGL.getInput().addAction(new UserAction("MOVE") {
                @Override
                protected void onAction() {
                    super.onAction();
                    Point2D mouse = FXGL.getInput().getMousePositionWorld();
                    GameManager.player.getComponent(CustomAStarMoveComponent.class).moveToCell(GameManager.grid.pfg.getCell(mouse));
                }
            }, MouseButton.PRIMARY);
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

        FXGL.getInput().addAction(new UserAction("CHECK STATE") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                Point2D mouse = FXGL.getInput().getMousePositionWorld();
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
