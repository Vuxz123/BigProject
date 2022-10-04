package com.ethnicthv.bigproject.entity;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.component.GameMechanicComponent;
import com.ethnicthv.bigproject.event.events.UpdateBlockEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TestFactory implements EntityFactory {
    @Spawns("0,empty")
    public Entity empty(SpawnData data){
        int gs = GameManager.grid.gridsize;
        var view = new Rectangle(gs, gs, Color.WHITE);
        view.setStroke(Color.color(0, 0, 0, 0.25));
        view.setStrokeType(StrokeType.INSIDE);

        var e = entityBuilder(data)
                .view(view)
                .zIndex(0)
                .type(EntityType.NULL)
                .build();

        int x = GameManager.grid.getGridX((int) data.getX());
        int y = GameManager.grid.getGridY((int) data.getY());

        e.getViewComponent().addEventHandler(UpdateBlockEvent.UB, event -> {
            System.out.printf("%d %d : \n", x, y);
            if(x == event.getCellX() && y == event.getCellY()) {
                view.setFill(event.getColor());
            }
        });

        return e;
    }
    @Spawns("w,wall")
    public Entity wall(SpawnData data){
        int gs = GameManager.grid.gridsize;
        return FXGL.entityBuilder(data)
                .type(EntityType.WALL)
                .zIndex(0)
                .viewWithBBox(new Rectangle(gs,gs, Color.CHOCOLATE))
                .collidable()
                .build();
    }
    @Spawns("mechanic")
    public Entity mechanic(SpawnData data){
        return FXGL.entityBuilder(data)
                .at(0,0)
                .view(TextureProvider.INSTANCE.FRAME)
                .zIndex(30)
                .with(new GameMechanicComponent())
                .build();
    }
}
