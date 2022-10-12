package com.ethnicthv.bigproject.util;

import com.almasb.fxgl.dsl.FXGL;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.entities.enemy.EnemyPool;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.security.InvalidParameterException;

public class Util {

    public static int SS(final int size) {
        if (size < 1) throw new InvalidParameterException("" + size + " is not valid");
        int g = GameManager.grid.gridsize;
        return size % g != 0 ? (size / g + 1) * g : size;
    }

    public static void setBlockChange(int cellX, int cellY, Color color) {
        FXGL.getGameWorld().getEntitiesAt(new Point2D(cellX * 16 + 5, cellY * 16 + 5)).forEach(e -> {
            if (e.getType() == EntityType.NULL) {
                Rectangle r = (Rectangle) e.getViewComponent().getChildren().get(0);
                r.setFill(color);
            }
        });
    }

    public static void spawnNPC(int x, int y) {
//        var e = entityBuilder()
//                .viewWithBBox(new Rectangle(16, 16, FXGLMath.randomColor()))
//                .type(EntityType.ENTITY)
//                .at(5 + x*16, 5 + y*16)
//                .anchorFromCenter()
//                .with(new CustomCellMoveComponent(GameManager.OFFSETX, GameManager.OFFSETY ,GameManager.grid.gridsize, GameManager.grid.gridsize, 150))
//                .with(new CustomAStarMoveComponent(GameManager.grid.pfg))
//                .with(new CustomRandomAStarMoveComponent(Duration.seconds(0), Duration.seconds(0.5)))
//                .with(new StateComponent())
//                .with(new AIComponent())
//                .collidable()
//                .buildAndAttach();
        EnemyPool.spawnCommonEntity(x, y);
    }

}
