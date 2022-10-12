package com.ethnicthv.bigproject.client;

import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.entity.entities.Player;
import com.ethnicthv.bigproject.entity.entities.SealedPlayer;
import com.ethnicthv.bigproject.physic.PhysicControler;
import com.ethnicthv.bigproject.physic.collision.*;
import com.ethnicthv.bigproject.ui.CooldownIcon;
import com.ethnicthv.bigproject.ui.ProgressBar;
import com.ethnicthv.bigproject.ui.StackingCooldownIcon;
import com.sun.media.jfxmedia.logging.Logger;
import javafx.scene.paint.Color;

import java.net.URISyntaxException;

import static com.ethnicthv.bigproject.util.Util.SS;

public class GameManager {
    public static Player player;
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

    public static void init() {
        //init item

    }

    public static void setting(GameSettings gameSettings) {
        WIDTH = SS(WIDTH);
        HEIGHT = SS(HEIGHT);
        gameSettings.setWidth(WIDTH + OFFSETX * 2);
        gameSettings.setHeight(HEIGHT + OFFSETY * 2);
        gameSettings.setTitle(TITLE);
        gameSettings.setVersion(VERSION);
        gameSettings.setTicksPerSecond(TICKS);
        gameSettings.setDeveloperMenuEnabled(true);
        gameSettings.setMainMenuEnabled(true);
        System.out.println("" + grid.getMaxGridX() + " " + grid.getMaxGridY());
    }

    public static void initGame() throws URISyntaxException {
        Logger.setLevel(Logger.DEBUG);
        FXGL.getEventBus().setLoggingEnabled(true);
        grid.setup();
        player = new Player().init();
        FXGL.getGameWorld().spawn("mechanic");
        FXGL.getGameWorld().addEntity(player);
        PhysicControler.INSTACNE.add("a", new EntityPCollision());
        PhysicControler.INSTACNE.add("b", new EtoECollision());
        PhysicControler.INSTACNE.add("d", new PlayerToItemCollision());
        PhysicControler.INSTACNE.add("e", new ParticletoBlockCollision());
        PhysicControler.INSTACNE.add("f", new PtoParCollision());
        PhysicControler.INSTACNE.add("g", new PtoECollision());
        PhysicControler.INSTACNE.add("h", new PARTICLEtWallCollision());
        PhysicControler.INSTACNE.add("i", new PtoBCollision());
        grid.resetLevel();
    }

    public static void initUI() {
        ui.text = FXGL.addText("0", WIDTH - 100, 500);
        ui.shield = new CooldownIcon(TextureProvider.INSTANCE.SHIELD_ICON.copy(), player.getPlayerData()::getShielddelay, 20);
        ui.shield.setTranslateX(OFFSETX + grid.gridsize * grid.maxGridX + ((double) grid.maxGridX / 2));
        ui.shield.setTranslateY(OFFSETY + grid.gridsize * 6 + ((double) grid.maxGridY / 2));
        ui.speed = new CooldownIcon(TextureProvider.INSTANCE.SPEED_ICON.copy(), player.getPlayerData()::getSpeedUpdelay, 10);
        ui.speed.setTranslateX(OFFSETX + grid.gridsize * grid.maxGridX + ((double) grid.maxGridX / 2) + 32);
        ui.speed.setTranslateY(OFFSETY + grid.gridsize * 6 + ((double) grid.maxGridY / 2));
        ui.health = new ProgressBar(Color.RED, player.getPlayerData()::getHealth, 100);
        ui.health.setTranslateX(OFFSETX + grid.gridsize * grid.maxGridX + 8);
        ui.health.setTranslateY(OFFSETY + grid.gridsize + ((double) grid.maxGridY / 2));
        ui.mana = new ProgressBar(Color.BLUE, player.getPlayerData()::getMana, 100);
        ui.mana.setTranslateX(OFFSETX + grid.gridsize * grid.maxGridX + 8);
        ui.mana.setTranslateY(OFFSETY + grid.gridsize * 4 - 8 + ((double) grid.maxGridY / 2));
        ui.boom = new StackingCooldownIcon(TextureProvider.INSTANCE.BOOM_ICON.copy(), player.getPlayerData()::getBomcooldown, 10, player.getPlayerData()::getBoms, 5);
        ui.boom.setTranslateX(OFFSETX + grid.gridsize * grid.maxGridX + ((double) grid.maxGridX / 2) + 64);
        ui.boom.setTranslateY(OFFSETY + grid.gridsize * 6 + ((double) grid.maxGridY / 2));
        ui.block = new StackingCooldownIcon(TextureProvider.INSTANCE.BLOCK_ICON, player.getPlayerData()::getBlockcooldown, 10, player.getPlayerData()::getBlocks, 5);
        ui.block.setTranslateX(OFFSETX + grid.gridsize * grid.maxGridX + ((double) grid.maxGridX / 2) + 96);
        ui.block.setTranslateY(OFFSETY + grid.gridsize * 6 + ((double) grid.maxGridY / 2));
        FXGL.getGameScene().addUINode(ui.shield);
        FXGL.getGameScene().addUINode(ui.speed);
        FXGL.getGameScene().addUINode(ui.health);
        FXGL.getGameScene().addUINode(ui.mana);
        FXGL.getGameScene().addUINode(ui.boom);
        FXGL.getGameScene().addUINode(ui.block);
    }

    public static void onUpdate(double tdf) {
        GameManager.ui.text.setText(String.valueOf(GameManager.player.getPlayerData().getHealth()));
    }

    public static SealedPlayer getPlayer() {
        return player;
    }

    public static void onPlayerDeath() {

    }
}
