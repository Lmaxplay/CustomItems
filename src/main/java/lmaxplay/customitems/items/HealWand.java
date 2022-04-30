package lmaxplay.customitems.items;

import lmaxplay.customitems.CustomItem;
import lmaxplay.customitems.ItemType;
import lmaxplay.customitems.Mana;
import lmaxplay.customitems.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
                "§7This will apply a regeneration effect",
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
        if(Mana.getMana(player) > 70 && Mana.getMana(player) > 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10, 3));
            Mana.removeMana(player, 70);
        } else {
            player.sendMessage(ChatColor.RED + "You do not have enough mana!");
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
}
