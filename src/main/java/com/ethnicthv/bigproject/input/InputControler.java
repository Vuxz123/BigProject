package com.ethnicthv.bigproject.input;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.MouseButton;

public class InputControler {
    public static final InputControler INSTANCE = new InputControler();

    private InputControler(){}

    @Deprecated
    public void setup(){
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
}
