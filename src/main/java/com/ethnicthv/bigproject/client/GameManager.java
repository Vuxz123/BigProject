package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.ethnicthv.bigproject.input.InputControler;
import com.ethnicthv.bigproject.physic.PhysicControler;
import com.ethnicthv.bigproject.physic.collision.EntityPCollision;
import com.ethnicthv.bigproject.physic.collision.EtoECollision;
import com.ethnicthv.bigproject.util.Util;
import com.ethnicthv.bigproject.entity.entities.Player;
import com.sun.media.jfxmedia.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

import static com.ethnicthv.bigproject.util.Util.SS;

public class GameManager {
    public static Entity player ;
    public static GameData data = new GameData();
    public static GameUI ui = new GameUI();
    public static final int OFFSETX = 5;
    public static final int OFFSETY = 5;
    public static int WIDTH = 1000;
    public static int HEIGHT = 500;
    public static final int TICKS = 64;
    public static final String TITLE = "Bomman";
    public static final String VERSION = "0.1";

    public static GameMap grid = new GameMap();

    private GameManager() {
    }

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

    public static void initGame() throws URISyntaxException {
        Logger.setLevel(Logger.DEBUG);
        FXGL.getEventBus().setLoggingEnabled(true);
        grid.setup();
        player = new Player().getPlayer();
        FXGL.getGameWorld().spawn("mechanic");
        FXGL.getGameWorld().addEntity(player);
        PhysicControler.INSTACNE.add("a", new EntityPCollision());
        PhysicControler.INSTACNE.add("b", new EtoECollision());
        Util.spawnNPC(10,10);
        Util.spawnNPC(20,20);

    }

    public static void initUI(){
        ui.text = FXGL.addText("0", WIDTH - 100, 0 + 100);
    }



    public static void onUpdate(double tdf) {
        GameManager.ui.text.setText(String.valueOf(GameManager.data.killed));
        GameManager.ui.text.setText(String.valueOf(InputControler.INSTANCE.getBombPlaced()));
    }

}
