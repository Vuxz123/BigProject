package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultController implements UIController {

    @FXML
    Label nameLabel;
    @FXML
    Label highestScore;

    public void displayName(String username, int Score) {
        nameLabel.setText("Yo: " + username);
        highestScore.setText("Your score is: " + Score);

    }

    @Override
    public void init() {

    }
}