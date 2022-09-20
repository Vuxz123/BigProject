package com.ethnicthv.bigproject;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.ethnicthv.bigproject.entity.TestFactory;
import com.ethnicthv.bigproject.input.InputControler;
import com.ethnicthv.bigproject.physic.PhysicControler;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class HelloApplication extends GameApplication {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    public static Text count;
    public static Viewport externalViewPort = new Viewport(100,100);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
        gameSettings.setTicksPerSecond(128);
        //gameSettings.setIntroEnabled(true);
    }

    @Override
    protected void initUI() {
        super.initUI();
        FXGL.getGameScene();
        count = FXGL.addText("0",10,15);
        count.setFill(Paint.valueOf("red"));
    }

    @Override
    protected void initGame() {
        super.initGame();
        FXGL.getGameWorld().addEntityFactory(new TestFactory());
        FXGL.getGameScene().setBackgroundColor(Paint.valueOf("blue"));
        InputControler.INSTANCE.setup();
    }

    @Override
    protected void initPhysics() {
        super.initPhysics();
        PhysicControler.INSTACNE.setup();
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
        count.setText(String.valueOf(FXGL.getGameWorld().getEntities().size()));
    }
}