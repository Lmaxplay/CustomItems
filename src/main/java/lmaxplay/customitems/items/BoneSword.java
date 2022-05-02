package lmaxplay.customitems.items;

import lmaxplay.customitems.ItemFlags;
import lmaxplay.customitems.ItemType;
import lmaxplay.customitems.CustomItem;
import lmaxplay.customitems.Rarity;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BoneSword implements CustomItem {

    @Override
    public String getName() {
        return "§5Bone Sword";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7A sword made of bones.",
                "§5§lEPIC WEAPON"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "BONE_SWORD";
    }

    @Override
    @NotNull
    public Boolean hasAbility() {
        return false;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.Legendary;
    }

    @Override
    public ItemType getType() {
        return ItemType.Weapon;
    }

    @Override
    public ItemStack createItemStack() {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.BONE);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(getName());
        meta.setLore(getLore());
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
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
        return null;
    }
}
