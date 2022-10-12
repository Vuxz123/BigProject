package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;

public class FXGLMenuDIY extends FXGLMenu {
    public static UI mainMenu;

    public static UI soundMenu;
    public static UI optionMenu;

    public FXGLMenuDIY() {
        super(MenuType.MAIN_MENU);
        MenuController menuController = new MenuController();
        mainMenu = FXGL.getAssetLoader().loadUI("Menu.fxml", menuController);

        getRoot().getChildren().add(mainMenu.getRoot());

        OptionController optionController = new OptionController();

        // 2. place fxml file in "assets/ui" and load it
        optionMenu = getAssetLoader().loadUI("Option.fxml", optionController);

        GameSoundMenuController gameSoundMenuController = new GameSoundMenuController();
        soundMenu = getAssetLoader().loadUI("GameSoundMenu.fxml", gameSoundMenuController);
    }

    public UI getLoader() {
        return mainMenu;
    }
}
