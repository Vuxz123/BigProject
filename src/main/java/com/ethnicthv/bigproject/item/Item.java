package com.ethnicthv.bigproject.item;

import com.almasb.fxgl.inventory.ItemStack;
import com.almasb.fxgl.texture.Texture;

public abstract class Item {
    private final Texture texture;

    public Item(Texture texture) {
        this.texture = texture;
    }

    public int maxItem(){
        return 5;
    }

    public abstract ItemStack getItemStack();

    public abstract void run();

    public Texture getTexture() {
        return texture;
    }
}
