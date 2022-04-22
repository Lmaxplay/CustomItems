package lmaxplay.customitems;

import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum Rarity {
    Common,
    Uncommon,
    Rare,
    Epic,
    Legendary,
    Mythic,
    Special,
    Unique,
    Unknown;

    private static final Map<String, Rarity> rarityMap = Map.of(
            "common", Common,
            "uncommon", Uncommon,
            "rare", Rare,
            "epic", Epic,
            "legendary", Legendary,
            "mythic", Mythic,
            "special", Special,
            "unique", Unique,
            "unknown", Unknown
    );

    public static final Map<Rarity, Double> rarityMultiplierMap = Map.of(
            Common, 1.0,
            Uncommon, 1.2,
            Rare, 1.5,
            Epic, 1.8,
            Legendary, 2.0,
            Mythic, 2.2,
            Special, 2.5,
            Unique, 3.0,
            Unknown, 1.0
    );

    public static final Map<Rarity, String> color = Map.of(
            Common, "§a",
            Uncommon, "§b",
            Rare, "§9",
            Epic, "§5",
            Legendary, "§6",
            Mythic, "§d",
            Special, "§5",
            Unique, "§4",
            Unknown, "§f"
    );

    public String getName() {
        return name().toLowerCase();
    }

    public String getNameUpper() { return name().toUpperCase(); }

    @NotNull
    public Double getMultiplier() {
        return rarityMultiplierMap.get(this);
    }

    @NotNull
    public String getColor() {
        return color.get(this);
    }

    public static void addRarity(ItemStack itemStack, @NotNull Rarity rarity) {
        if(itemStack == null) {
            return;
        }

        if(itemStack.getItemMeta() == null) {
            return;
        }

        List<String> lore = itemStack.getItemMeta().getLore();

        if(lore == null) {
            lore = new ArrayList<String>();
        }

        lore.add(rarity.getColor() + "§l" + rarity.getName().toUpperCase() + " " + ItemType.getItemType(itemStack.getType()).getNameUpper());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        if(!itemMeta.hasDisplayName()) {
            String name = itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : itemStack.getType().name().replace("_", " ").toLowerCase();
        }
        //itemMeta.setDisplayName(rarity.getColor() + itemMeta.getDisplayName());
        itemStack.setItemMeta(itemMeta);

    }

    public static Rarity getRarity(ItemStack itemStack) {
        if(itemStack == null) {
            return Unknown;
        }

        if(itemStack.getItemMeta() == null) {
            return Unknown;
        }

        List<String> lore = itemStack.getItemMeta().getLore();

        if(lore == null) {
            return Unknown;
        }

        for(String line : lore) {
            if(line.contains(ItemType.getItemType(itemStack.getType()).getNameUpper())) {
                for(Rarity rarity : rarityMap.values()) {
                    if(line.contains(rarity.getNameUpper())) {
                        return rarity;
                    }
                }
            }
        }

        return Unknown;
    }
}
