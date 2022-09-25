package com.ethnicthv.bigproject.entity.entities;

import com.almasb.fxgl.entity.Entity;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import com.ethnicthv.bigproject.entity.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.debug;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class Player extends Entity {

    public Player(){
        this.setType(EntityType.PLAYER);
    }

    public Entity getPlayer(){
        var agent = entityBuilder()
                .viewWithBBox(new Rectangle(16, 16, Color.BLUE))
                .at(5 + 32, 5 + 32)
                .with(new CustomCellMoveComponent(5, 5,16, 16, 150))
                .with(new CustomAStarMoveComponent(GameManager.grid.pfg))
                .zIndex(1)
                .anchorFromCenter()
                .build();

        agent.getComponent(CustomCellMoveComponent.class).atDestinationProperty().addListener((o, old, isAtDestination) -> {
            if (isAtDestination) {
                debug("CellMoveComponent: reached destination");
            }
        });

        agent.getComponent(CustomAStarMoveComponent.class).atDestinationProperty().addListener((o, old, isAtDestination) -> {
            if (isAtDestination) {
                debug("CustomAStarMoveComponent: reached destination");
            }
        });

        return agent;
    }

}
