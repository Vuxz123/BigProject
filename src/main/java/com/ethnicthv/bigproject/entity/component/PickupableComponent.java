package com.ethnicthv.bigproject.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.item.Item;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;

public class PickupableComponent extends Component {

    private final BooleanProperty pickupable = new SimpleBooleanProperty(false);

    private final Item item;

    private final DropShadow effect = new DropShadow();

    private double time = 0;

    private int dir = 1;

    public PickupableComponent(Item item) {
        this.item = item;
    }

    @Override
    public void onAdded() {
        super.onAdded();
        if (!entity.getType().toString().equals(EntityType.ITEMENTITY.toString()))
            throw new RuntimeException("Entity is not allow to have PickupableComponent!");
        Point2D start = new Point2D(0, -this.entity.getY() - 50);
        Point2D end = new Point2D(0, 0);
        this.entity.getViewComponent().getChildren().forEach((c) -> c.setEffect(effect));
        FXGL.animationBuilder()
                .onFinished(() -> this.pickupable.setValue(true))
                .duration(Duration.seconds(3))
                .translate(this.entity.getViewComponent().getChildren())
                .from(start).to(end).buildAndPlay();
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        effect.setSpread(time / 15);
        double speed = 5;
        tpf *= speed;
        time = time + dir * tpf;
        if (dir == 1) dir = (time > 10) ? -1 : 1;
        else if (dir == -1) dir = (time < 0) ? 1 : -1;
    }

    public boolean isPickupable() {
        return pickupable.get();
    }

    public BooleanProperty pickupableProperty() {
        return pickupable;
    }

    public Item getItem() {
        return item;
    }
}
