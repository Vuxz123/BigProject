package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.LevelLoader;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.ethnicthv.bigproject.Util;
import com.ethnicthv.bigproject.client.map.CustomTextLevelLoader;
import com.ethnicthv.bigproject.client.map.MappingFunction;
import com.ethnicthv.bigproject.entity.FactoryManager;
import com.ethnicthv.bigproject.entity.TestFactory;

import static com.ethnicthv.bigproject.client.GameManager.*;

public class GameMap {
    public final int gridsize = 16;

    public final int mapSizeX = 16* 40;

    public AStarGrid pfg;

    public Level level;

    private LevelLoader loader;
    public final int maxGrixX = GameManager.WIDTH / gridsize + 1; // 62
    public final int maxGridY = GameManager.HEIGHT / gridsize + 1; // 31

    public GameMap() {}

    public void setup(){
        FactoryManager.INSTANCE.addFactory(new TestFactory());
        loader = new CustomTextLevelLoader(gridsize, gridsize, OFFSETX, OFFSETY,'0');
        level = FXGL.getAssetLoader().loadLevel("test-level.txt", loader);
        pfg = AStarGrid.fromWorld(FXGL.getGameWorld(), maxGrixX, maxGridY, gridsize, gridsize, MappingFunction::apply);
        FXGL.getGameWorld().setLevel(level);

        Util.spawnNPC(2,2);
    }

    public int getGridsize() {
        return gridsize;
    }

    public int getMaxGrixX() {
        return maxGrixX;
    }

    public int getMaxGridY() {
        return maxGridY;
    }

}
