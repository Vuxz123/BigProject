package com.ethnicthv.bigproject.client;

import java.io.Serializable;

public class Pos implements Serializable {
    private static final long serialVersionUID = 1234L;

    private int posX, posY;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void add(Pos other){
        this.posX += other.posX;
        this.posY += other.posY;
    }
}
