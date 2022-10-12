package com.ethnicthv.bigproject.item.items;

import com.almasb.fxgl.inventory.ItemStack;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.item.Item;

public class HealthItem extends Item {
    public HealthItem() {
        super(TextureProvider.INSTANCE.HEALTH.copy());
    }

    @Override
    public ItemStack getItemStack() {
        return null;
    }

    @Override
    public void run() {
        GameManager.getPlayer().getPlayerData().addHealth(50);
    }
}
