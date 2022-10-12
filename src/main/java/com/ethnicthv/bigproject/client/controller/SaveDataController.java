package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxgl.ui.UIController;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.client.ResourceManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class SaveDataController implements Initializable, UIController {

    @FXML
    Button loginButton;
    @FXML
    TextField nameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void init() {

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void login(Event event) throws Exception {


        ResourceManager.INSTANCE.add(nameTextField.getText());

        ResultController resultController = new ResultController();
        UI loader = FXGL.getAssetLoader().loadUI("Result.fxml", resultController);
        resultController.displayName(nameTextField.getText(), GameManager.data.killed);
        FXGL.getGameScene().addUI(loader);
//        ResultController scene2Controller = mainMenu.getController();
//        scene2Controller.displayName(username, highestScore);
//
//        //root = FXMLLoader.load(getClass().getResource("Result.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        GameData gameData = new GameData();
//        gameData.name = nameTextField.getText();
//        gameData.score= 13;// jj do . score
//        try {
//            ResourceManager.save(1L, "1.save");
//        }
//        catch (Exception e) {
//            System.out.println("Couldnt save" + e.getMessage());
//        }
//        try{
//            GameData data = new GameData();
//            data.name= ((GameData) ResourceManager.load("1.save")).name;
//            nameTextField.setText(data.name);
//            data.toString();
//        }
//        catch (Exception e) {
//            System.out.println("couldnt load save data " + e.getMessage());
//        }
//        FXMLLoader mainMenu = new FXMLLoader(getClass().getResource("Result.fxml"));
//        root = mainMenu.load();
//
//        ResultController scene2Controller = mainMenu.getController();
//
//        scene2Controller.displayName(gameData.name, (String.valueOf(gameData.score)));
//
//        //root = FXMLLoader.load(getClass().getResource("Result.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

}