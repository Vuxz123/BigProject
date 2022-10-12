package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

public class SceneFactoryDIY extends SceneFactory {


    @NotNull
    @Override
    public FXGLMenu newMainMenu() {

        return new FXGLMenuDIY();
    }

//    @NotNull
//    @Override
//    public FXGLMenu newGameMenu() {
//        MenuController menuController = new MenuController();
//        UI mainMenu = FXGL.getAssetLoader().loadUI("Menu.fxml", menuController);
//        return null;
//    }
}
