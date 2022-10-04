package com.ethnicthv.bigproject.ui;

import com.almasb.fxgl.core.Updatable;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.ethnicthv.bigproject.client.GameManager;
import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

public class CooldownIcon extends Group implements Updatable {
    private Rectangle rec;
    private Scale scale;
    public CooldownIcon(Texture texture) {
        super(texture);
        var list = this.getChildren();
        rec = new Rectangle(texture.getWidth(),texture.getHeight(), Color.WHITE);
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
        scale.setY(GameManager.getPlayer().getPlayerData().getShielddelay() / 10);
    }
}
