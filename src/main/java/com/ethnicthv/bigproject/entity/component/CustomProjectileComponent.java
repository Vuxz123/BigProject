package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.CopyableComponent;
import com.ethnicthv.bigproject.entity.component.graphic.GraphicComponent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;

public class CustomProjectileComponent extends Component implements CopyableComponent<CustomProjectileComponent> {
    private Class<? extends GraphicComponent> type;
    private GraphicComponent graphicComponent;
    private Point2D direction;
    private Double speed;
    private DoubleProperty speedProp;
    private Point2D velocity;
    private Boolean isAllowRotation = true;

    private ChangeListener<Number> speedListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newSpeed) {
            velocity = velocity.normalize().multiply(newSpeed.doubleValue());
            updateRotation();
        }
    };

    public CustomProjectileComponent(Point2D direction, Double speed, Class<? extends GraphicComponent> type) {
        this.direction = direction;
        this.speed = speed;
        this.velocity = direction.normalize().multiply(speed);
        this.speedProp =  new SimpleDoubleProperty(speed);
        this.type = type;
    }

    public CustomProjectileComponent() {
        this(new Point2D(1.0, 0.0), 1.0, GraphicComponent.class);
    }

    public Point2D getDirection() {
        return velocity.normalize();
    }

    public void setDirection(Point2D direction) {
        velocity = direction.normalize().multiply(speed);
        updateRotation();
    }

    public Double getSpeed() {
        return speedProp.getValue();
    }

    public void setSpeed(Double speed) {
        this.speedProp.set(speed);
    }

    public CustomProjectileComponent setAllowRotation(Boolean allowRotation) {
        isAllowRotation = allowRotation;
        return this;
    }

    public DoubleProperty speedProperty() {
        return speedProp;
    }

    private void updateRotation() {
        if (isAllowRotation) {
            entity.rotateToVector(velocity);
            graphicComponent.rotateToVector(velocity);
        }
    }

    @Override
    public void onAdded() {
        super.onAdded();
        graphicComponent = this.entity.getComponent(type);
        updateRotation();
        this.speedProp.addListener(speedListener);
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        this.entity.translate(velocity.multiply(tpf));
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        this.speedProp.removeListener(speedListener);
    }

    @Override
    public CustomProjectileComponent copy() {
        return new CustomProjectileComponent(direction, speed, graphicComponent.getClass());
    }

    @Override
    public boolean isComponentInjectionRequired() {
        return false;
    }
}
