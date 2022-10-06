package com.ethnicthv.bigproject.client;

import javafx.beans.property.DoubleProperty;

public class PlayerData {
    private Double Health = 100.0;
    private Double Mana = 100.0;
    private Double Bomdelay = (double) 0;
    private Double Shielddelay = (double) 0;
    private Double SpeedUpdelay = (double) 0;

    public void onUpdate(double tpf) {
        if (Bomdelay > 0) Bomdelay -= tpf;
        else Bomdelay = (double) 0;

        if (Shielddelay > 0) Shielddelay -= tpf;
        else Shielddelay = (double) 0;

        if (SpeedUpdelay > 0) SpeedUpdelay -= tpf;
        else SpeedUpdelay = (double) 0;
    }

    public boolean isBomDelay() {
        return Bomdelay > 0;
    }

    public boolean isShielddelay() {
        return Shielddelay > 0;
    }

    public boolean isSpeedUpdelay() {
        return SpeedUpdelay > 0;
    }

    public void resetBomDelay() {
        this.Bomdelay = 0.5;
    }

    public void resetShieldDelay() {
        this.Shielddelay = 20.0;
    }

    public void resetSpeedUpdelay() {
        this.SpeedUpdelay = 10.0;
    }

    public Double getHealth() {
        return Health;
    }

    public Double getMana() {
        return Mana;
    }

    public Double getBomdelay() {
        return Bomdelay;
    }

    public Double getShielddelay() {
        return Shielddelay;
    }

    public Double getSpeedUpdelay() {
        return SpeedUpdelay;
    }
}
