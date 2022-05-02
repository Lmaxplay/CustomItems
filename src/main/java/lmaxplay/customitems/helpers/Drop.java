package lmaxplay.customitems.helpers;

import lmaxplay.customitems.CustomItem;
import org.bukkit.inventory.ItemStack;

public class Drop {
    private CustomItem item;
    private int min;
    private int max;
    private double chance;

    public Drop(CustomItem item, int min, int max, double chance) {
        this.item = item;
    }

    public CustomItem getItem() {
        return item;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getChance() {
        return chance;
    }

    public ItemStack drop() {
        // Generate a random number of items to drop using chance
        int num;
        if (Math.random() <= chance) {
            num = (int) (Math.random() * (max - min + 1)) + min;
        } else {
            num = 0;
        }

        ItemStack drops = item.createItemStack();
        drops.setAmount(Math.min(num, 64));
        // Drop the items
        return drops;
    }

}
