package lmaxplay.customitems.items;

import lmaxplay.customitems.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ObsidianPickaxe implements CustomItem {
    @Override
    public String getName() {
        return "§6Obsidian Pickaxe";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7A pickaxe made of",
                "§7compressed obsidian.",
                "§7Ability: §6Haste II",
                "§7Mana cost: §610",
                "§7Gives you a §6Haste II §7buff for §610 §7seconds.",
                "§6§lLEGENDARY PICKAXE"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "OBSIDIAN_PICKAXE";
    }

    @Override
    public @NotNull Boolean hasAbility() {
        return true;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.Legendary;
    }

    @Override
    public ItemType getType() {
        return ItemType.Pickaxe;
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getName());
        meta.setLore(getLore());
        itemStack.setItemMeta(meta);
        itemStack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
        itemStack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10);
        return itemStack;
    }

    @Override
    public void use(ItemStack itemStack, Player player) {
        if(Mana.getMana(player) >= 10) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 10, 1));
            Mana.removeMana(player, 10);
        }
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
        return null;
    }


}
