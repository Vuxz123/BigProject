package com.ethnicthv.bigproject.client;

public class PlayerData {

    private double Bomdelay = 0;

    private double Shielddelay = 0;

    private double SpeedUpdelay = 0;

    public void onUpdate(double tpf) {
        if (Bomdelay > 0) Bomdelay -= tpf;
        else Bomdelay = 0;

        if (Shielddelay > 0) Shielddelay -= tpf;
        else Shielddelay = 0;
    }

    public boolean isBomDelay() {
        return Bomdelay > 0;
    }

    public boolean isShielddelay() {
        return Shielddelay > 0;
    }

    public boolean isSpeedUpdelay() {
        return Shielddelay > 0;
    }

    public void resetBomDelay() {
        this.Bomdelay = 0.5;
    }

    public void resetShieldDelay() {
        this.Shielddelay = 10;
    }

    public void resetSpeedUpdelay() {
        this.SpeedUpdelay = 10;
    }

    public double getBomdelay() {
        return Bomdelay;
    }

    public double getShielddelay() {
        return Shielddelay;
    }

    public double getSpeedUpdelay() {
        return SpeedUpdelay;
    }
}
