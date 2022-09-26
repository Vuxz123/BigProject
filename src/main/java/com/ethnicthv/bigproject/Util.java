package com.ethnicthv.bigproject;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.state.StateComponent;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.component.ai.AIComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomAStarMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomCellMoveComponent;
import com.ethnicthv.bigproject.entity.component.pdf.CustomRandomAStarMoveComponent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
                .type(EntityType.ENTITY)
                .at(5 + x*16, 5 + y*16)
                .anchorFromCenter()
                .with(new CustomCellMoveComponent(GameManager.OFFSETX, GameManager.OFFSETY ,GameManager.grid.gridsize, GameManager.grid.gridsize, 150))
                .with(new CustomAStarMoveComponent(GameManager.grid.pfg))
                .with(new CustomRandomAStarMoveComponent(Duration.seconds(1), Duration.seconds(3)))
                .with(new StateComponent())
                .with(new AIComponent())
                .collidable()
                .buildAndAttach();


    }

}
