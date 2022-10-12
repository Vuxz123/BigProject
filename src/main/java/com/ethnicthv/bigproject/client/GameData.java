package com.ethnicthv.bigproject.client;

import java.io.Serializable;

public class GameData {
    public int killed = 0;
    public int score;

    public void reset(){
        killed = 0;
        score = 0;
    }
}
