package com.ethnicthv.bigproject.item;

import com.almasb.fxgl.inventory.ItemStack;

import java.util.HashMap;

public class ItemRegistryManager {
    public static ItemRegistryManager INSTANCE = new ItemRegistryManager();
    private final HashMap<String, Item> data = new HashMap<>();

    private ItemRegistryManager() {
    }

    public void registry(String name, Item item) {
        data.put(name, item);
    }

    public ItemStack getItemStack(String name) {
        return data.get(name).getItemStack();
    }
}
