package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultController implements UIController {


    @FXML
    Label scoreLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label highestScore;
    public void display(String username, int Score, String highestScore) {
        nameLabel.setText("Yo: " + username);
        this.highestScore.setText("Your highest score is: " + highestScore);

        scoreLabel.setText("Your score is " + Score);

    }

    @Override
    public void init() {

    }
}