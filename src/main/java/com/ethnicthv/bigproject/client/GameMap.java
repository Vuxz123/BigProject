package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.LevelLoader;
import com.ethnicthv.bigproject.client.map.CustomTextLevelLoader;
import com.ethnicthv.bigproject.client.map.MappingFunction;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import com.ethnicthv.bigproject.entity.FactoryManager;
import com.ethnicthv.bigproject.entity.TestFactory;
import com.ethnicthv.bigproject.entity.boom.BomFactory;
import com.ethnicthv.bigproject.entity.particle.ParticleFactory;

import static com.ethnicthv.bigproject.client.GameManager.*;

public class GameMap {
    public final int gridsize = 16;

    public Level level;

    private LevelLoader loader;
    public final int maxGridX = 48; // 46
    public final int maxGridY = GameManager.HEIGHT / gridsize + 1; // 32

    public SafeGrid pfg = new SafeGrid(maxGridX,maxGridY);

    public GameMap() {}

    public void setup(){
        FactoryManager.INSTANCE.addFactory(new TestFactory());
        FactoryManager.INSTANCE.addFactory(new BomFactory());
        FactoryManager.INSTANCE.addFactory(new ParticleFactory());
        loader = new CustomTextLevelLoader(gridsize, gridsize, OFFSETX, OFFSETY,'1', MappingFunction::apply);
        level = FXGL.getAssetLoader().loadLevel("map1", loader);
        FXGL.getGameWorld().setLevel(level);
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
