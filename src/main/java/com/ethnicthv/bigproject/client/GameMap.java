package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.LevelLoader;
import com.ethnicthv.bigproject.client.map.CustomTextLevelLoader;
import com.ethnicthv.bigproject.client.map.MappingFunction;
import com.ethnicthv.bigproject.client.map.SafeCellState;
import com.ethnicthv.bigproject.client.map.SafeGrid;
import com.ethnicthv.bigproject.entity.FactoryManager;
import com.ethnicthv.bigproject.entity.TestFactory;
import com.ethnicthv.bigproject.entity.boom.BlockBoom;
import com.ethnicthv.bigproject.entity.boom.BomFactory;
import com.ethnicthv.bigproject.entity.entities.enemy.EnemyPool;
import com.ethnicthv.bigproject.entity.particle.ParticleFactory;
import com.ethnicthv.bigproject.item.ItemEntityFactory;
import com.ethnicthv.bigproject.util.Pos;

import static com.ethnicthv.bigproject.client.GameManager.OFFSETX;
import static com.ethnicthv.bigproject.client.GameManager.OFFSETY;

public class GameMap {
    public final int gridsize = 16;
    public final int maxGridX = 48; // 46
    public final int maxGridY = GameManager.HEIGHT / gridsize + 1; // 32
    public SafeGrid pfg = new SafeGrid(maxGridX, maxGridY);
    public final int max_entity = 10;
    public int numberOfEntities = 10;
    public Level level;

    public GameMap() {
    }

    public void setup() {
        FactoryManager.INSTANCE.addFactory(new TestFactory());
        FactoryManager.INSTANCE.addFactory(new BomFactory());
        FactoryManager.INSTANCE.addFactory(new ParticleFactory());
        FactoryManager.INSTANCE.addFactory(new ItemEntityFactory());
        LevelLoader loader = new CustomTextLevelLoader(gridsize, gridsize, OFFSETX, OFFSETY, '1', MappingFunction::apply);
        level = FXGL.getAssetLoader().loadLevel("map3", loader);
        FXGL.getGameWorld().setLevel(level);
    }

    public void resetLevel() {
        {
            int i = 7;
            int j = 2;
            for (Pos p : BlockBoom.pair) {
                int x = i + p.getKey();
                int y = j + p.getValue();
                if (GameManager.grid.pfg.get(x, y).getState() != SafeCellState.NOT_WALKABLE) {
                    //System.out.println("" + p.getKey() + " " + p.getValue() + " " + GameManager.grid.pfg.get(x, y).isWalkable() + " " + x + " " + y + " " + GameManager.grid.pfg.get(x, y).getState());
                    FXGL.spawn("block", x * GameManager.grid.gridsize + GameManager.OFFSETX, y * GameManager.grid.gridsize + GameManager.OFFSETY);
                }
            }

            i = 2;
            j = 7;
            for (Pos p : BlockBoom.pair) {
                int x = i + p.getKey();
                int y = j + p.getValue();
                if (GameManager.grid.pfg.get(x, y).getState() != SafeCellState.NOT_WALKABLE) {
                    //System.out.println("" + p.getKey() + " " + p.getValue() + " " + GameManager.grid.pfg.get(x, y).isWalkable() + " " + x + " " + y + " " + GameManager.grid.pfg.get(x, y).getState());
                    FXGL.spawn("block", x * GameManager.grid.gridsize + GameManager.OFFSETX, y * GameManager.grid.gridsize + GameManager.OFFSETY);
                }
            }
        }
        for (int v = 0; v < 30; v++) {
            var lcell = pfg.getWalkableCell();
            var cell = FXGLMath.random(lcell);
            if (cell.isEmpty() || cell.get().getWorldPosition().distance(pfg.get(2, 2).getWorldPosition()) < 100) {
                v--;
                continue;
            }
            int i = cell.get().getX() - 1;
            int j = cell.get().getY() - 1;
            for (Pos p : BlockBoom.pair) {
                int x = i + p.getKey();
                int y = j + p.getValue();
                if (GameManager.grid.pfg.get(x, y).getState() != SafeCellState.NOT_WALKABLE) {
                    //System.out.println("" + p.getKey() + " " + p.getValue() + " " + GameManager.grid.pfg.get(x, y).isWalkable() + " " + x + " " + y + " " + GameManager.grid.pfg.get(x, y).getState());
                    FXGL.spawn("block", x * GameManager.grid.gridsize + GameManager.OFFSETX, y * GameManager.grid.gridsize + GameManager.OFFSETY);
                }
            }
        }
        var lcell = pfg.getWalkableCell();
        for (int v = 0; v < 10; v++) {
            var cell = FXGLMath.random(lcell);
            if (cell.isEmpty() || cell.get().getWorldPosition().distance(pfg.get(2, 2).getWorldPosition()) < 150) {
                v--;
                continue;
            }
            EnemyPool.spawnCommonEntity(cell.get().getX(), cell.get().getY());
        }
    }

    public int getGridX(int x) {
        return x / gridsize;
    }

    public int getGridY(int y) {
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
