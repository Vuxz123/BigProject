package com.ethnicthv.bigproject.client;

public class PlayerData {
    private final double maxHealth = 100;
    private final double maxMana = 100;
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
        this.Shielddelay = 5.0;
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

    public boolean hasEnoughMana(double amount) {
        return this.Mana - amount > 0 ;
    }

    public boolean useMana(double amount) {
        this.Mana = this.Mana - amount;
        if(this.Mana < 0) {
            this.Mana += amount;
            return false;
        }
        return true;
    }

    public boolean dealDamage(double damage) {
        if(GameManager.getPlayer().getPCC().isInvincible()) return false;
        this.Health = this.Health - damage;
        if(this.Health <= 0d) {
            this.Health = 0d;
            return true;
        }
        return false;
    }
}
