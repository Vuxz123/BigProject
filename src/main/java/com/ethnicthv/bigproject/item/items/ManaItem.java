package com.ethnicthv.bigproject.item.items;

import com.almasb.fxgl.inventory.ItemStack;
import com.ethnicthv.bigproject.asset.TextureProvider;
import com.ethnicthv.bigproject.client.GameManager;
import com.ethnicthv.bigproject.item.Item;

public class ManaItem extends Item {
    public ManaItem() {
        super(TextureProvider.INSTANCE.MANA.copy());
    }

    @Override
    public ItemStack getItemStack() {
        return null;
    }

    @Override
    public void run() {
        GameManager.getPlayer().getPlayerData().addMana(50);
    }
}
