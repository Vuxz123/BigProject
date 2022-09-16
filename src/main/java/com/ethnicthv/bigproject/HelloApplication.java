package com.ethnicthv.bigproject;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Paint;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
        gameSettings.setTicksPerSecond(64);
    }

    @Override
    protected void initUI() {
        super.initUI();
        count = FXGL.addText("0",0,0);
        count.setFill(Paint.valueOf("red"));
        count.setX(20);
        count.setY(5);
        FXGL.centerTextBind(count);
    }

    @Override
    protected void initGame() {
        super.initGame();
        FXGL.getGameWorld().addEntityFactory(new TestFactory());
        FXGL.getInput().addAction(new UserAction("alo") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                //if(FXGL.getGameWorld().getEntitiesByType(Type.ROCKET).size() > 1) return;
                FXGL.getGameWorld().spawn(("Projectile"));
                super.onAction();
            }
        }, MouseButton.PRIMARY);
        FXGL.getInput().addAction(new UserAction("ola") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                //if(FXGL.getGameWorld().getEntitiesByType(Type.ROCKET).size() > 1) return;
                FXGL.getGameWorld().spawn(("Block"));
                super.onAction();
            }
        }, MouseButton.SECONDARY);
    }

    @Override
    protected void initPhysics() {
        super.initPhysics();
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.BLOCK, Type.ROCKET) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                super.onCollisionBegin(a, b);
                b.removeFromWorld();
            }
        });
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
        count.setText(String.valueOf(FXGL.getGameWorld().getEntities().size()));
    }
}