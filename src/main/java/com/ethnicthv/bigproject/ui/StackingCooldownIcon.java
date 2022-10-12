package com.ethnicthv.bigproject.ui;

import com.almasb.fxgl.texture.Texture;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.function.Supplier;

public class StackingCooldownIcon extends CooldownIcon {
    final Supplier<Integer> stacklisten;
    final Text amount;

    public StackingCooldownIcon(Texture texture, Supplier<Double> valuelisten, double max, Supplier<Integer> stacklisten, int maxstack) {
        super(texture, valuelisten, max);
        this.stacklisten = stacklisten;
        this.amount = new Text(String.valueOf(maxstack));
        this.amount.setFill(Color.RED);
        this.amount.setX(32 - amount.getLayoutX() - 8);
        this.amount.setY(32 - amount.getLayoutY());
        this.amount.setText("");
        this.getChildren().add(amount);
    }

    @Override
    public void onUpdate(double v) {
        super.onUpdate(v);
        this.amount.setText(String.valueOf(stacklisten.get()));
    }
}
