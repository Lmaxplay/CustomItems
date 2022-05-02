package lmaxplay.customitems.items;

import lmaxplay.customitems.ItemType;
import lmaxplay.customitems.Mana;
import lmaxplay.customitems.Rarity;
import lmaxplay.customitems.CustomItemStrings;
import lmaxplay.customitems.*;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class LaunchWand implements CustomItem {

    @Override
    public String getName() {
        return "§6Launch Wand";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7Ability: §6Launch",
                "§7Right click to launch yourself",
                "§7in the direction you are looking",
                "§7in.",
                "§7Cost: §610 mana",
                "§6§lLEGENDARY WAND"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "LAUNCH_WAND";
    }

    @Override
    @NotNull
    public Boolean hasAbility() {
        return true;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.Legendary;
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
        if(Mana.getMana(player) > 10) {
            Location loc = player.getLocation();
            Vector dir = loc.getDirection();
            dir.normalize();
            dir.multiply(5); //5 blocks a way
            loc.add(dir);
            player.setVelocity(player.getEyeLocation().getDirection().multiply(3));
            Mana.removeMana(player, 10);
        } else {
            player.sendMessage(CustomItemStrings.notEnoughMana);
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
