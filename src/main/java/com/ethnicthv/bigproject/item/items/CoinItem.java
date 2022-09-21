package com.ethnicthv.bigproject.item.items;

import com.almasb.fxgl.inventory.ItemStack;
import com.almasb.fxgl.texture.Texture;
import com.ethnicthv.bigproject.item.Item;

public class CoinItem extends Item {
    public CoinItem(Texture texture) {
        super(texture);
    }

    @Override
    public int maxItem() {
        return 100;
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack<>(this);
    }
}
