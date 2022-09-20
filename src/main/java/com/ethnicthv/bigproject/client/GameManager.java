package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.app.GameSettings;
import com.ethnicthv.bigproject.entity.entities.Player;

public class GameManager {
    public static final Player player = new Player();
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int TICKS = 64;
    public static final String TITLE = "Bomman";
    public static final String VERSION = "0.1";

    public static GameMap grid = new GameMap();

    private GameManager(){}

    public static void init(){

    }

    public static void setting(GameSettings gameSettings){
        gameSettings.setWidth(WIDTH);
        gameSettings.setHeight(HEIGHT);
        gameSettings.setTitle(TITLE);
        gameSettings.setVersion(VERSION);
        gameSettings.setTicksPerSecond(TICKS);
    }

}
