package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.ethnicthv.bigproject.Util;
import com.ethnicthv.bigproject.entity.entities.Player;

import static com.ethnicthv.bigproject.Util.SS;

public class GameManager {
    public static Entity player ;

    public static final int OFFSETX = 5;
    public static final int OFFSETY = 5;
    public static int WIDTH = 1000;
    public static int HEIGHT = 500;
    public static final int TICKS = 64;
    public static final String TITLE = "Bomman";
    public static final String VERSION = "0.1";

    public static GameMap grid = new GameMap();

    private GameManager(){}

    public static void init(){
        //init item

    }

    public static void setting(GameSettings gameSettings){
        WIDTH = SS(WIDTH);
        HEIGHT = SS(HEIGHT);
        gameSettings.setWidth(WIDTH + OFFSETX * 2);
        gameSettings.setHeight(HEIGHT + OFFSETY * 2);
        gameSettings.setTitle(TITLE);
        gameSettings.setVersion(VERSION);
        gameSettings.setTicksPerSecond(TICKS);
        System.out.println("" + grid.getMaxGridX() + " " + grid.getMaxGridY());
    }

    public static void initGame(){
        grid.setup();
        player = new Player().getPlayer();
        FXGL.getGameWorld().addEntity(player);
        Util.spawnNPC(10,10);
    }

}
