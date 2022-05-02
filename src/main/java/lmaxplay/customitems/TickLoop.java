package lmaxplay.customitems;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TickLoop implements Runnable {
    public void run() {
        // add a rarity to every player's inventory
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getHealth() > 0) {
                player.setHealth(Math.min(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), player.getHealth() + (Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue() / 400)));
            }
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null) {
                    // Make every item in the player's inventory be unbreakable
                    if( item.getItemMeta() instanceof Damageable ) {
                        Damageable meta = (Damageable) item.getItemMeta();
                        meta.setUnbreakable(true);
                        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                        item.setItemMeta(meta);
                    }

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
            world.setGameRule(GameRule.NATURAL_REGENERATION, false);
            for (org.bukkit.entity.Item item : world.getEntitiesByClass(org.bukkit.entity.Item.class)){
                ItemStack itemStack = item.getItemStack();
                if (itemStack != null) {
                    boolean hasRarity = false;

                    if( itemStack.getItemMeta() instanceof Damageable ) {
                        Damageable meta = (Damageable) itemStack.getItemMeta();
                        meta.setUnbreakable(true);
                        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                        itemStack.setItemMeta(meta);
                    }

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
