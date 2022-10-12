package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.UI;
import com.ethnicthv.bigproject.client.controller.GameSoundMenuController;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

public class GameSubScene extends SubScene
{
    public GameSubScene() {
        super();
        // 1. create a controller class that implements UIController
        GameSoundMenuController controller = new GameSoundMenuController();

        // 2. place fxml file in "assets/ui" and load it
        UI fxmlUI = getAssetLoader().loadUI("GameSoundMenu.fxml", controller);

        // 4. add UI to game scene
        getGameScene().addUI(fxmlUI);
        getContentRoot().getChildren().add(fxmlUI.getRoot());
    }
}
