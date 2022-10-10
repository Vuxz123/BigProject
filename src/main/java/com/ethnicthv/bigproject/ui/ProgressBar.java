package com.ethnicthv.bigproject.ui;

import com.almasb.fxgl.core.Updatable;
import com.almasb.fxgl.texture.Texture;
import com.ethnicthv.bigproject.asset.TextureProvider;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import java.util.function.Supplier;

public class ProgressBar extends Group implements Updatable {
    private final Supplier<Double> valuelisten;

    private Node processbar;
    private final double max;

    private Scale scale;

    public ProgressBar(Node texture, Supplier<Double> valuelisten, double max) {
        super(texture);
        this.valuelisten = valuelisten;
        this.max = max;
        var list = this.getChildren();
        this.processbar = list.get(0);
        scale = new Scale();
        scale.setPivotY(0);
        scale.setPivotX(0);
        processbar.getTransforms().add(scale);
        list.add(TextureProvider.INSTANCE.BARFRAME.copy());
    }

    public ProgressBar(Color color ,Supplier<Double> valuelisten, double max) {
        this(new Rectangle(16*13, 32, color), valuelisten, max);
    }

    @Override
    public void onUpdate(double tpf) {

        scale.setX(valuelisten.get() / max);

    }
}
