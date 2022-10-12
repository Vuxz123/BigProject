package com.ethnicthv.bigproject.client.pri;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.controller.SceneFactoryDIY;
import com.ethnicthv.bigproject.entity.FactoryManager;
import com.ethnicthv.bigproject.input.InputControler;
import com.ethnicthv.bigproject.physic.PhysicControler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URISyntaxException;

public class BommerApplication extends GameApplication {
    public static Text count;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        GameManager.setting(gameSettings);
        //gameSettings.setIntroEnabled(true);
        //gameSettings.setGameMenuEnabled(false);
        //gameSettings.setGameMenuEnabled(false);
        //gameSettings.setSoundMenuBack("level.wav");
        GameManager.init();
        gameSettings.setSceneFactory(new SceneFactoryDIY());
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setGameMenuEnabled(false);
    }

    @Override
    protected void initUI() {
        super.initUI();
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
        GameManager.initUI();


//        SaveDataController controller = new SaveDataController();
//
//        // 2. place fxml file in "assets/ui" and load it
//        UI fxmlUI = getAssetLoader().loadUI("SaveData.fxml", controller);
//
//
//        // 4. add UI to game scene
//        getGameScene().addUI(fxmlUI);

        //getContentRoot().getChildren().add(fxmlUI.getRoot());
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initGame() {
        super.initGame();

        FactoryManager.INSTANCE.setup();
        InputControler.INSTANCE.setup();
        try {
            GameManager.initGame();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initPhysics() {
        super.initPhysics();
        PhysicControler.INSTACNE.setup();
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
        GameManager.onUpdate(tpf);
    }
}