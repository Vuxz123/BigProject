package com.ethnicthv.bigproject.entity.entities.enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.ethnicthv.bigproject.client.GameManager;

import java.util.LinkedList;

public class EnemyPool {
    public static final EnemyPool INSTANCE = new EnemyPool();
    final LinkedList<CommonEnemy> commonEnemiesPool = new LinkedList<>();
    private int max = 10;

    public static void spawnCommonEntity(int x, int y) {
        CommonEnemy entity = INSTANCE.newCommonEnemyInstance();
        entity.setPosition(GameManager.OFFSETX + x * GameManager.grid.gridsize, GameManager.OFFSETX + y * GameManager.grid.gridsize);
        FXGL.getGameWorld().addEntity(entity);
    }

    public static void killCommonEntity(Entity enemy) {
        if (enemy instanceof CommonEnemy commonEnemy) {
            FXGL.getGameWorld().getEntities().remove(commonEnemy);
            INSTANCE.clearCommonEnemy(commonEnemy);
        }
    }

    public CommonEnemy newCommonEnemyInstance() {
        if (commonEnemiesPool.isEmpty()) return new CommonEnemy();
        CommonEnemy entity = commonEnemiesPool.getLast();
        commonEnemiesPool.removeLast();
        return entity;
    }

    public void clearCommonEnemy(CommonEnemy commonEnemy) {
        if (commonEnemiesPool.size() >= 10) return;
        commonEnemy.reset();
        commonEnemiesPool.addFirst(commonEnemy);
    }

}
