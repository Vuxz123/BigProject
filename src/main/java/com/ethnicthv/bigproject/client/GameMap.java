package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.LevelLoader;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.ethnicthv.bigproject.Util;
import com.ethnicthv.bigproject.client.map.CustomTextLevelLoader;
import com.ethnicthv.bigproject.entity.EntityType;
import com.ethnicthv.bigproject.entity.FactoryManager;
import com.ethnicthv.bigproject.entity.TestFactory;

import static com.ethnicthv.bigproject.client.GameManager.*;

public class GameMap {
    public final int gridsize = 16;

    public final int mapSizeX = 16* 40;

    public Level level;

    private LevelLoader loader;
    public final int maxGridX = GameManager.WIDTH / gridsize + 1; // 62
    public final int maxGridY = GameManager.HEIGHT / gridsize + 1; // 31

    public AStarGrid pfg = new AStarGrid(maxGridX,maxGridY);

    public GameMap() {}

    public void setup(){
        FactoryManager.INSTANCE.addFactory(new TestFactory());
        loader = new CustomTextLevelLoader(gridsize, gridsize, OFFSETX, OFFSETY,'1');
        //loader = new TextLevelLoader(gridsize,gridsize,'1');
        level = FXGL.getAssetLoader().loadLevel("test-level.txt", loader);
        pfg = AStarGrid.fromWorld(FXGL.getGameWorld(), maxGridX, maxGridY, gridsize, gridsize, (type) -> type.equals(EntityType.BLOCK)  ? CellState.NOT_WALKABLE : CellState.WALKABLE);
        FXGL.getGameWorld().setLevel(level);

        Util.spawnNPC(2,2);
    }

    public int getGridX(int x){
        return x / gridsize;
    }

    public int getGridY(int y){
        return y / gridsize;
    }

    public int getGridsize() {
        return gridsize;
    }

    public int getMaxGridX() {
        return maxGridX;
    }

    public int getMaxGridY() {
        return maxGridY;
    }

}
