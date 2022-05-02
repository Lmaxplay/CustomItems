package lmaxplay.customitems.items;

import lmaxplay.customitems.*;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class IronGolemSword implements CustomItem {

    @Override
    public String getName() {
        return "§6Iron Golem Sword";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7A sword made from the finest iron.",
                "§7Ability: §6Iron Push",
                "§7Flings your foes away from you",
                "§7with a powerful force.",
                "§7Cost: §6100 mana",
                "§7Radius: §616 blocks",
                "§6§lLEGENDARY WEAPON"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "IRON_GOLEM_SWORD";
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
        return ItemType.Sword;
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.IRON_SWORD);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getName());
        meta.setLore(getLore());
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 32.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void use(ItemStack itemStack, Player player) {
        if (Mana.getMana(player) >= 100) {
            for (Entity ent : player.getNearbyEntities(16.0D, 16.0D, 16.0D)) {
                if (ent instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) ent;
                    if (player.hasLineOfSight(target)) {
                        ent.setVelocity(ent.getVelocity().add(player.getLocation().getDirection().multiply(6D)).add(new org.bukkit.util.Vector(0, 2, 0)));
                    }
                }
            }
            // Play a sound
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 1.0F, 1.0F);
            Mana.removeMana(player, 100);
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
