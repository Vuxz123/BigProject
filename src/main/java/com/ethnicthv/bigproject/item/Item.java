package com.ethnicthv.bigproject.item;

import com.almasb.fxgl.texture.Texture;

public abstract class Item {
    private Texture texture;

    public Item(Texture texture) {
        this.texture = texture;
    }

    public int maxItem(){
        return 5;
    }

    public abstract ItemStack getItemStack();
}
