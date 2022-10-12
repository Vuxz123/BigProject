package com.ethnicthv.bigproject.client.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
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
import java.util.Comparator;
import java.util.ResourceBundle;


public class SaveDataController implements Initializable, UIController {

    @FXML
    Button loginButton;
    @FXML
    TextField nameTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void init() {

    }

    @FXML
    public void login(Event event) throws Exception {

        ResultController resultController = new ResultController();
        UI loader = FXGL.getAssetLoader().loadUI("Result.fxml", resultController);
        System.out.println(GameManager.data.killed);
        ResourceManager.Data hs = ResourceManager.INSTANCE.playerData.stream().filter((data -> data.getName().equals(nameTextField.getText()))).max(Comparator.comparingInt(ResourceManager.Data::getScore))
                .orElseGet(() -> new ResourceManager.Data(0, nameTextField.getText()));
        resultController.display(nameTextField.getText(), GameManager.data.killed, "" + hs.getScore());
        ResourceManager.INSTANCE.add(nameTextField.getText());
        FXGL.getWindowService().popSubScene();
        SubScene scene = new SubScene() {
            @Override
            public void onCreate() {
                super.onCreate();
                this.getRoot().getChildren().add(loader.getRoot());
            }
        };
        ResourceManager.INSTANCE.save();

        FXGL.getWindowService().pushSubScene(scene);

    }

}