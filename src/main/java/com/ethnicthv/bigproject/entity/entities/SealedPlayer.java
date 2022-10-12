package com.ethnicthv.bigproject.entity.entities;

import com.almasb.fxgl.entity.Entity;
import com.ethnicthv.bigproject.client.PlayerData;
import com.ethnicthv.bigproject.entity.component.PlayerControlerComponent;
import javafx.geometry.Point2D;

public interface SealedPlayer {
    PlayerData getPlayerData();

    Point2D getPosition();

    Entity toEntity();

    PlayerControlerComponent getPCC();

}
