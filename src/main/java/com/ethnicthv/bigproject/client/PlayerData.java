package com.ethnicthv.bigproject.client;

public class PlayerData {
    private final double maxHealth = 100;
    private final double maxMana = 100;
    private Double Health = 100.0;
    private Double Mana = 100.0;
    private Double Bomdelay = (double) 0;
    private Double Shielddelay = (double) 0;
    private Double SpeedUpdelay = (double) 0;
    private Double Bomcooldown = 10.0;
    private Integer Boms = 5;
    private Double Blockcooldown = 10.0;
    private Integer Blocks = 5;
    private Double boomDuration = 250d;
    private Double boomSpe = 200d;


    public void onUpdate(double tpf) {
        if (Health <= 0) {
            GameManager.onPlayerDeath();
        }
        if (Bomdelay > 0) Bomdelay -= tpf;
        else Bomdelay = (double) 0;

        if (Shielddelay > 0) Shielddelay -= tpf;
        else Shielddelay = (double) 0;

        if (SpeedUpdelay > 0) SpeedUpdelay -= tpf;
        else SpeedUpdelay = (double) 0;

        if (Bomcooldown > 0) Bomcooldown -= tpf;
        else {
            if (Boms >= 5) {
                Bomcooldown = (double) 0;
            } else {
                Bomcooldown = 10d;
                Boms++;
            }
        }
        if (Blockcooldown > 0) Blockcooldown -= tpf;
        else {
            if (Blocks >= 5) {
                Blockcooldown = (double) 0;
            } else {
                Blockcooldown = 10d;
                Blocks++;
            }
        }
    }

    public Double getBoomDuration() {
        return boomDuration;
    }

    public void setBoomDuration(Double boomDuration) {
        this.boomDuration = boomDuration;
    }

    public Double getBoomSpe() {
        return boomSpe;
    }

    public void setBoomSpe(Double boomSpe) {
        this.boomSpe = boomSpe;
    }

    public boolean placeBoom() {
        if (this.Boms - 1 < 0) return false;
        this.Boms--;
        return true;
    }

    public boolean placeBlock() {
        if (this.Blocks - 1 < 0) return false;
        this.Blocks--;
        return true;
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

    public Integer getBoms() {
        return Boms;
    }

    public Integer getBlocks() {
        return Blocks;
    }

    public Double getBomcooldown() {
        return Bomcooldown;
    }

    public Double getBlockcooldown() {
        return Blockcooldown;
    }

    public boolean hasEnoughMana(double amount) {
        return this.Mana - amount > 0;
    }

    public void addHealth(double amount) {
        this.Health += amount;
        if (this.Health > 100) this.Health = 100.0;
    }

    public void addMana(double amount) {
        this.Mana += amount;
        if (this.Mana > 100) this.Mana = 100.0;
    }

    public boolean useMana(double amount) {
        this.Mana = this.Mana - amount;
        if (this.Mana < 0) {
            this.Mana += amount;
            return false;
        }
        return true;
    }

    public boolean dealDamage(double damage) {
        if (GameManager.getPlayer().getPCC().isInvincible()) return false;
        this.Health = this.Health - damage;
        if (this.Health <= 0d) {
            this.Health = 0d;
            return true;
        }
        return false;
    }
}
