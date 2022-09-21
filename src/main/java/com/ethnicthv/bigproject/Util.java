package com.ethnicthv.bigproject;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.RandomAStarMoveComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.ethnicthv.bigproject.client.GameManager;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.jetbrains.annotations.TestOnly;

import java.security.InvalidParameterException;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class Util {

    public static int SS(final int size){
        if(size < 1) throw new InvalidParameterException( "" + size + " is not valid");
        int g = GameManager.grid.gridsize;
        return size % g != 0 ? (size / g + 1) * g : size;
    }

    public static void spawnNPC(int x, int y) {
        var e = entityBuilder()
                .viewWithBBox(new Rectangle(16, 16, FXGLMath.randomColor()))
                .anchorFromCenter()
                .with(new CellMoveComponent(GameManager.grid.gridsize, GameManager.grid.gridsize, 150))
                .with(new AStarMoveComponent(GameManager.grid.pfg))
                .with(new RandomAStarMoveComponent(1, 7, Duration.seconds(1), Duration.seconds(3)))
                .buildAndAttach();

        e.getComponent(AStarMoveComponent.class).stopMovementAt(x, y);
    }

}
