package com.ethnicthv.bigproject.client.controller;


import com.almasb.fxgl.app.scene.*;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxgl.ui.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class OptionController implements UIController, Initializable {

    @FXML
    private Button Music;
    @FXML
    private ChoiceBox<String> controlChoiceBox;
    @FXML
    private Button Back;
    OptionController() {
    }
    private String[] selection = {"MOUSE", "KEYBOARD"};

    @Override
    public void init() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controlChoiceBox.getItems().addAll("MOUSE", "KEYBOARD");
        //System.out.println(controlChoiceBox.toString());
    }

    public void music() {
        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().clear();
        // 4. add UI to game scene
        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().add(FXGLMenuDIY.soundMenu.getRoot());
    }
//    public void getSelection(ActionEvent event) {
//
//        String myFood = choiceBox.getValue();
//    }

    public void control (ActionEvent event) {

    }

    public void back(ActionEvent event) {
        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().clear();
        // 4. add UI to game scene
        FXGL.getWindowService().getCurrentScene().getRoot().getChildren().add(FXGLMenuDIY.mainMenu.getRoot());
    }
}
