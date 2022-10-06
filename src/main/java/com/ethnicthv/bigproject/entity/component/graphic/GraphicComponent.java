package com.ethnicthv.bigproject.entity.component.graphic;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.Effect;

public class GraphicComponent extends Component {
    private Entity graphic;
    private int OffsetX = 0;
    private int OffsetY = 0;

    public GraphicComponent(Entity graphic) {
        this.graphic = graphic;
    }

    public GraphicComponent(Node texture) {
        this.graphic = new Entity();
        graphic.getViewComponent().addChild(texture);
    }

    @Override
    public void onAdded() {
        super.onAdded();
        this.setScaleOrigin(new Point2D(8, 8));
        graphic.setPosition(entity.getPosition().add(new Point2D(OffsetX, OffsetY)));
        FXGL.getGameWorld().addEntity(graphic);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        this.graphic.setPosition(entity.getPosition().add(new Point2D(OffsetX, OffsetY)));
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        graphic.removeFromWorld();
    }

    public GraphicComponent setZIndex(int z) {
        this.graphic.setZIndex(z);
        return this;
    }

    public GraphicComponent setOffsetX(int offsetX) {
        this.OffsetX = offsetX;
        return this;
    }

    public GraphicComponent setOffsetY(int offsetY) {
        this.OffsetY = offsetY;
        return this;
    }

    public void setScaleOrigin(Point2D origin) {
        this.graphic.getTransformComponent().setScaleOrigin(origin);
    }

    public void mirror(boolean isleft) {
        if (isleft) this.graphic.getTransformComponent().setScaleX(-1);
        else this.graphic.getTransformComponent().setScaleX(1);
    }

    public int getOffsetX() {
        return OffsetX;
    }

    public int getOffsetY() {
        return OffsetY;
    }

    public Entity getGraphic() {
        return graphic;
    }

    public GraphicComponent setGraphic(Entity graphic) {
        this.graphic = graphic;
        return this;
    }

    public void addEffect(Effect effect, int index) {
        this.graphic.getViewComponent().getChildren().get(index).setEffect(effect);
    }
}
