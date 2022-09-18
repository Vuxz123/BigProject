package com.ethnicthv.bigproject;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
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
        Shape shape = new Rectangle(FXGL.getGameScene().getHeight(),FXGL.getGameScene().getWidth());
        shape.setFill(Paint.valueOf("red"));
        FXGL.addUINode(shape);
        count = FXGL.addText("0",10,15);
        count.setFill(Paint.valueOf("red"));
        //FXGL.getGameScene().addUINode(count);
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
                a.distance(b);
                Point2D c = a.getBoundingBoxComponent().getCenterWorld().subtract(b.getBoundingBoxComponent().getCenterWorld());
                Point2D d = b.getBoundingBoxComponent().getCenterWorld().subtract(c.getX()/2, c.getY()/2);
                FXGL.getGameWorld().spawn(("Explosion"), new SpawnData().put("x", d.getX()).put("y", d.getY()));
            }
        });
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
        count.setText(String.valueOf(FXGL.getGameWorld().getEntities().size()));
    }
}