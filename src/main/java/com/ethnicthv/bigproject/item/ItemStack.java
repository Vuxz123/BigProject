package com.ethnicthv.bigproject.item;

public class ItemStack {
    int amount = 0;
    String item_name;

    public ItemStack(int amount, String item_name) {
        this.amount = amount;
        this.item_name = item_name;
    }
}
