package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.UI;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

public class GameSubScene extends SubScene
{
    public GameSubScene() {
        super();
        // 1. create a controller class that implements UIController
        GameMenu controller = new GameMenu();

        // 2. place fxml file in "assets/ui" and load it
        UI fxmlUI = getAssetLoader().loadUI("hello-view.fxml", controller);

        // 4. add UI to game scene
        getGameScene().addUI(fxmlUI);
        getContentRoot().getChildren().add(fxmlUI.getRoot());
    }
}
