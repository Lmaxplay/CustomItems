package lmaxplay.customitems;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CustomItem {
    /**
     * Gets the name of the custom item.
     * @return The name of the custom item.
     */
    String getName();

    /**
     * Gets the lore of the custom item.
     * @return The lore of the custom item.
     */
    List<String> getLore();

    /**
     * Gets the custom ID of this item
     * @return The custom ID of this item
     */
    String getId();

    @NotNull
    Boolean hasAbility();

    /**
     * @return The custom item.
     */
    Rarity getRarity();

    /**
     * @return an ItemType that represents the type of this custom item.
     */
    ItemType getType();

    /**
     * @return The custom item.
     */
    ItemStack createItemStack();

    /**
     * Uses the ability of the item
     */
    void use(ItemStack itemStack, Player player);
}
