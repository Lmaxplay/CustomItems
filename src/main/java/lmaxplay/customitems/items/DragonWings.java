package lmaxplay.customitems.items;

import lmaxplay.customitems.*;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class DragonWings implements CustomItem {
    @Override
    public String getName() {
        return "§6Dragon Wings";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7Forged from the scales of a newborn dragon",
                "§7The wings of this legendary item are said to",
                "§7have the power to fly at the speed of light",
                "§7Ability: §6Launch",
                "§7Launches you in the direction you are looking",
                "§7Cost: §65 mana",
                "§6§lLEGENDARY ELYTRA"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "DRAGON_WINGS";
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
        return ItemType.Elytra;
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.ELYTRA);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getName());
        meta.setLore(getLore());
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
        meta.addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        // meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
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
        if(Mana.getMana(player) > 5) {
            Location loc = player.getLocation();
            Vector dir = loc.getDirection();
            dir.normalize();
            dir.multiply(5); //5 blocks a way
            loc.add(dir);
            player.setVelocity(player.getEyeLocation().getDirection().multiply(3));
            Mana.removeMana(player, 5);
        } else {
            player.sendMessage(CustomItemStrings.notEnoughMana);
        }
    }

    @Override
    public List<ItemFlags> getFlags() {
        ItemFlags[] flags = new ItemFlags[] {
                ItemFlags.FALL_IMMUNITY,
                ItemFlags.WALL_IMMUNITY
        };
        return Arrays.asList(flags);
    }

}
