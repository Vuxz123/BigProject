package com.ethnicthv.bigproject.ui;

import com.almasb.fxgl.core.Updatable;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import java.util.function.Supplier;

public class CooldownIcon extends Group implements Updatable {
    private final double max;
    private final Supplier<Double> valuelisten;
    private final Scale scale;

    public CooldownIcon(Texture texture, Supplier<Double> valuelisten, double max) {
        super(texture);
        this.valuelisten = valuelisten;
        this.max = max;
        var list = this.getChildren();
        Rectangle rec = new Rectangle(texture.getWidth(), texture.getHeight(), Color.WHITE);
        rec.setOpacity(0.5);
        scale = new Scale();
        scale.setPivotX(0);
        scale.setPivotY(0);
        rec.getTransforms().add(scale);
        scale.setY(0);
        list.add(rec);
    }

    @Override
    public void onUpdate(double v) {
        scale.setY(valuelisten.get() / max);
    }
}
