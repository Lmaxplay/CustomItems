package lmaxplay.customitems.items;

import lmaxplay.customitems.CustomItem;
import lmaxplay.customitems.ItemType;
import lmaxplay.customitems.Rarity;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BoneSwordRefined implements CustomItem {

    @Override
    public String getName() {
        return "§6Refined Bone Sword";
    }

    @Override
    public List<String> getLore() {
        String[] lore = new String[] {
                "§7A sword made of bones,",
                "§7refined to an incredible level.",
                "§6§lLEGENDARY WEAPON"
        };
        return Arrays.asList(lore);
    }

    @Override
    public String getId() {
        return "BONE_SWORD_REFINED";
    }

    @Override
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
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 16.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void use(ItemStack itemStack, Player player) {
    }
}
