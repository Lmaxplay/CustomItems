package lmaxplay.customitems.items;

import lmaxplay.customitems.CustomItem;
import lmaxplay.customitems.ItemType;
import lmaxplay.customitems.Mana;
import lmaxplay.customitems.Rarity;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ManaBow implements CustomItem {

    @Override
    public String getName() {
        return "§5Mana Bow";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7A bow made using pure mana.",
                "§7Ability: §6Mana Arrow",
                "§7Mana cost: §6100",
                "§7Uses up to §6100§7 mana per shot to increase damage.",
                "§7If 100 mana is used, the arrow will always be a critical hit.",
                "§5§lEPIC BOW"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "MANA_BOW";
    }

    @Override
    public @NotNull Boolean hasAbility() {
        return true;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.Rare;
    }

    @Override
    public ItemType getType() {
        return null;
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.BOW);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getName());
        meta.setLore(getLore());
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void use(ItemStack itemStack, Player player) {}

    @Override
    public void shoot(ItemStack itemStack, Entity projectile, Player player) {
        if(projectile instanceof Arrow) {
            if(Mana.getMana(player) >= 1) {
                Arrow arrow = (Arrow) projectile;
                //arrow.setDamage(arrow.getDamage() + (player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() * (1 + (Math.min(Mana.getMana(player), 100) / 100))));
                if(Mana.getMana(player) >= 100) {
                    //arrow.setCritical(true);
                }

                Mana.removeMana(player, Math.min(Mana.getMana(player), 100));
            }
        }
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
