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


    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initGame() {
        super.initGame();


        try {
            FactoryManager.INSTANCE.setup();
            InputControler.INSTANCE.setup();
        } catch (IllegalArgumentException ignored) {
        }

        GameManager.initGame();
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