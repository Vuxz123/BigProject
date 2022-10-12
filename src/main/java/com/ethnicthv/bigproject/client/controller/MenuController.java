package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UIController;
import com.ethnicthv.bigproject.client.ResourceManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements UIController, Initializable {

    static public int count = 0;


    @FXML
    Button playButton;
    @FXML
    Button optionButton, exitButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    MenuController() {
    }

    @Override
    public void init() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResourceManager.INSTANCE.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void play(ActionEvent event) {
        if (count == 0) {
            FXGL.getWindowService().startNewGame();
            count++;
        } else {
            FXGL.getWindowService().gotoPlay();
        }
    }

    public void option(ActionEvent event) {

        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().clear();
        // 4. add UI to game scene
        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().add(FXGLMenuDIY.optionMenu.getRoot());

    }

    public void exit(ActionEvent event) throws Exception {
        ResourceManager.INSTANCE.save();
        Platform.exit();
    }
}
