package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UIController;
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
    @FXML
    Button playButton;

    @Override
    public void init() {

    }

    @FXML
    Button optionButton, exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    MenuController() {
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void play(ActionEvent event) throws IOException {

        FXGL.getWindowService().startNewGame();
    }

    public void option(ActionEvent event) throws IOException {

        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().clear();
        // 4. add UI to game scene
        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().add(FXGLMenuDIY.optionMenu.getRoot());

    }

    public void exit(ActionEvent event) throws IOException {
        Platform.exit();
    }
}
