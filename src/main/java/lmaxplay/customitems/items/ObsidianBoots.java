package lmaxplay.customitems.items;

import lmaxplay.customitems.CustomItem;
import lmaxplay.customitems.ItemFlags;
import lmaxplay.customitems.ItemType;
import lmaxplay.customitems.Rarity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ObsidianBoots implements CustomItem {
    @Override
    public String getName() {
        return "§9Obsidian Boots";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7Forged from refined enchanted obsidian",
                "§7Ability: §6Fireproof",
                "§7Makes you immune to fire damage",
                "§9§lRARE BOOTS"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "OBSIDIAN_BOOTS";
    }

    @Override
    public @NotNull Boolean hasAbility() {
        return false;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.Rare;
    }

    @Override
    public ItemType getType() {
        return ItemType.Leggings;
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.NETHERITE_BOOTS);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getName());
        meta.setLore(getLore());
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 5, true);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 5, true);
        meta.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 5, true);
        meta.addEnchant(Enchantment.DEPTH_STRIDER, 5, true);
        meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void use(ItemStack itemStack, Player player) {

    }

    @Override
    public void shoot(ItemStack itemStack, Entity projectile, Player player) {

    }

    @Override
    public void eat(ItemStack itemStack, Player player) {

    }

    @Override
    public void attack(ItemStack itemStack, Player player, Entity target) {

    }

    @Override
    public void crouch(ItemStack itemStack, Player player) {

    }

    @Override
    public List<ItemFlags> getFlags() {
        ItemFlags[] flags = new ItemFlags[] {
                ItemFlags.FIRE_IMMUNITY
        };
        return Arrays.asList(flags);
    }

}
