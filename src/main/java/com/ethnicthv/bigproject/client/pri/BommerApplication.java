package com.ethnicthv.bigproject.client.pri;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.FactoryManager;
import com.ethnicthv.bigproject.input.InputControler;
import com.ethnicthv.bigproject.physic.PhysicControler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BommerApplication extends GameApplication {
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
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initGame() {
        super.initGame();
        FactoryManager.INSTANCE.setup();
        InputControler.INSTANCE.setup();
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
    }
}