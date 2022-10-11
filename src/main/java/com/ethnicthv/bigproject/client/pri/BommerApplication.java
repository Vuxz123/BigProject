package com.ethnicthv.bigproject.client.pri;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.GameMenu;
import com.ethnicthv.bigproject.client.GameSubScene;
import com.ethnicthv.bigproject.entity.FactoryManager;
import com.ethnicthv.bigproject.input.InputControler;
import com.ethnicthv.bigproject.physic.PhysicControler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URISyntaxException;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

public class  BommerApplication extends GameApplication {
    public static Text count;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        GameManager.setting(gameSettings);
        //gameSettings.setIntroEnabled(true);
        GameManager.init();
    }

    @Override
    protected void initUI() {
        super.initUI();
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
        GameManager.initUI();
        GameSubScene gameSubScene= new GameSubScene();

        FXGL.getSceneService().pushSubScene(gameSubScene);
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