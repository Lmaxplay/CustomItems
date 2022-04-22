package lmaxplay.customitems;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.List;

public class TickLoop implements Runnable {
    public void run() {
        // add a rarity to every player's inventory
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null) {
                    boolean hasRarity = false;
                    List<String> lore = item.getItemMeta().getLore();

                    if (lore == null) {
                        lore = new ArrayList<String>();
                    }

                    for (String line : lore) {
                        for (Rarity rarity : Rarity.values()) {
                            if (line.contains(rarity.getName().toUpperCase() + " ")) {
                                hasRarity = true;
                            }
                        }
                    }
                    if(!hasRarity) {
                        Rarity.addRarity(item, Rarity.Common);
                    }
                }
            }
        }

        for (World world : Bukkit.getWorlds()) {
            for (org.bukkit.entity.Item item : world.getEntitiesByClass(org.bukkit.entity.Item.class)){
                ItemStack itemStack = item.getItemStack();
                if (itemStack != null) {
                    boolean hasRarity = false;
                    List<String> lore = itemStack.getItemMeta().getLore();

                    if (lore == null) {
                        lore = new ArrayList<String>();
                    }

                    for (String line : lore) {
                        for (Rarity rarity : Rarity.values()) {
                            if (line.contains(rarity.getName().toUpperCase() + " ")) {
                                hasRarity = true;
                            }
                        }
                    }
                    if(!hasRarity) {
                        // add a rarity to the lore
                        Rarity.addRarity(itemStack, Rarity.Common);
                    }
                }
            }
        }
    }
}
