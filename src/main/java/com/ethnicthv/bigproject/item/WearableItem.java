package com.ethnicthv.bigproject.item;

import com.almasb.fxgl.texture.Texture;

public abstract class WearableItem extends Item {

    public WearableItem(Texture texture) {
        super(texture);
    }

    @Override
    public int maxItem() {
        return 1;
    }

    public ItemStack getItemStack(){
        return null;
    }
}
