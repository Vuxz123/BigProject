package com.ethnicthv.bigproject.entity;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.pathfinding.CellState;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAstarMoveComponent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import static com.almasb.fxgl.core.math.FXGLMath.random;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TestFactory implements EntityFactory {
    @Spawns("0,empty")
    public Entity empty(SpawnData data){
        var view = new Rectangle(16, 16, Color.WHITE);
        view.setStroke(Color.color(0, 0, 0, 0.25));
        view.setStrokeType(StrokeType.INSIDE);

        var e = entityBuilder(data)
                .view(view)
                .build();

        int x = GameManager.grid.getGridX((int) data.getX());
        int y = GameManager.grid.getGridY((int) data.getY());

        e.getViewComponent().addOnClickHandler(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                GameManager.player.getComponent(CustomAstarMoveComponent.class).moveToCell(GameManager.grid.getGridX((int) data.getX()), GameManager.grid.getGridY((int) data.getY()));

            } else if (event.getButton() == MouseButton.SECONDARY) {
                GameManager.grid.pfg.get(x, y).setState(CellState.NOT_WALKABLE);
                view.setFill(Color.RED);
            }
        });

        return e;
    }
    @Spawns("w,wall")
    public Entity wall(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.BLOCK)
                .viewWithBBox(new Rectangle(16,16, Color.RED))
                .build();
    }
}
