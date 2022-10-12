package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.UI;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.controller.FXGLMenuDIY;
import com.ethnicthv.bigproject.client.controller.SaveDataController;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

public class InputControler {

    public static final InputControler INSTANCE = new InputControler();

    private InputControler() {
    }

    public void getControlOption(ControlOption c) {
    }

    @SuppressWarnings("DeprecatedIsStillUsed")
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
        }, KeyCode.NUMPAD0);
        //if (c.equals(MOUSE)) {

        //}
        // if (c.equals(KEYBOARD)) {
        FXGL.getInput().addAction(new UserAction("OPEN MENU") {
            @Override
            protected void onAction() {
                // 4. add UI to game scene
                FXGL.getWindowService().popSubScene();
                SubScene scene = new SubScene() {
                    @Override
                    public void onCreate() {
                        super.onCreate();
                        this.getRoot().getChildren().add(FXGLMenuDIY.soundMenu.getRoot());
                    }
                };

                FXGL.getWindowService().pushSubScene(scene);
            }
        }, KeyCode.N);
        FXGL.getInput().addAction(new UserAction("GO RIGHT") {
            @Override
            protected void onAction() {
                super.onAction();

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
        }, KeyCode.Q);
        FXGL.getInput().addAction(new UserAction("GO MENU") {
            @Override
            protected void onActionEnd() {
                FXGL.getWindowService().popSubScene();
                SubScene scene = new SubScene() {
                    @Override
                    public void onCreate() {
                        super.onCreate();
                        this.getRoot().getChildren().add(FXGLMenuDIY.mainMenu.getRoot());
                    }
                };

                FXGL.getWindowService().pushSubScene(scene);

            }
        }, KeyCode.P);
        FXGL.getInput().addAction(new UserAction("PLACE BOOM") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().placeBoom();
            }
        }, KeyCode.SPACE);


        FXGL.getInput().addAction(new UserAction("SPEED UP") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().speedUP();
            }
        }, KeyCode.E);

        FXGL.getInput().addAction(new UserAction("Test3") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                GameManager.getPlayer().getPCC().blockWay();
            }
        }, KeyCode.M);
    }
}
