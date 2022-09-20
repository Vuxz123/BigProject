package com.ethnicthv.bigproject.item.items;

import com.almasb.fxgl.texture.Texture;
import com.ethnicthv.bigproject.item.Item;
import com.ethnicthv.bigproject.item.ItemStack;

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
        return null;
    }
}
