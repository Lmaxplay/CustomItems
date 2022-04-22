package lmaxplay.customitems.items;

import lmaxplay.customitems.CustomItem;
import lmaxplay.customitems.ItemType;
import lmaxplay.customitems.Mana;
import lmaxplay.customitems.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HealWand implements CustomItem {


    @Override
    public String getName() {
        return "§9Healing wand";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7Right click to heal yourself",
                "§7Cost: §670 mana",
                "§9§lRARE WAND"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "HEAL_WAND";
    }

    @Override
    @NotNull
    public Boolean hasAbility() {
        return true;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.Rare;
    }

    @Override
    public ItemType getType() {
        return ItemType.Wand;
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.BLAZE_ROD);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getName());
        meta.setLore(getLore());
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void use(ItemStack itemStack, Player player) {
        if(Mana.getMana(player) > 70) {
            player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
            Mana.removeMana(player, 70);
        } else {
            player.sendMessage(ChatColor.RED + "You do not have enough mana!");
        }
    }
}
