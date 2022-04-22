package lmaxplay.customitems;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum ItemType {
    Bow,
    Sword,
    Axe,
    Pickaxe,
    Shovel,
    Hoe,
    Shears,
    Helmet,
    Chestplate,
    Leggings,
    Boots,
    Food,
    Potion,
    Armor,
    Tool,
    Fishing_Rod,
    Item,
    Trident,
    Elytra,
    Shield,
    Wand,
    Weapon
    ;

    public static final List<Material> bootsItems = Arrays.asList(
            Material.LEATHER_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_BOOTS,
            Material.DIAMOND_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.NETHERITE_BOOTS
    );

    public static final List<Material> leggingsItems = Arrays.asList(
            Material.LEATHER_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.DIAMOND_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.NETHERITE_LEGGINGS
    );

    public static final List<Material> chestplateItems = Arrays.asList(
            Material.LEATHER_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.NETHERITE_CHESTPLATE
    );

    public static final List<Material> helmetItems = Arrays.asList(
            Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.IRON_HELMET,
            Material.DIAMOND_HELMET,
            Material.GOLDEN_HELMET,
            Material.NETHERITE_HELMET
    );

    public static final List<Material> swordItems = Arrays.asList(
            Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.DIAMOND_SWORD,
            Material.GOLDEN_SWORD,
            Material.NETHERITE_SWORD
    );

    public static final List<Material> axeItems = Arrays.asList(
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.DIAMOND_AXE,
            Material.GOLDEN_AXE,
            Material.NETHERITE_AXE
    );

    public static final List<Material> pickaxeItems = Arrays.asList(
            Material.WOODEN_PICKAXE,
            Material.STONE_PICKAXE,
            Material.IRON_PICKAXE,
            Material.DIAMOND_PICKAXE,
            Material.GOLDEN_PICKAXE,
            Material.NETHERITE_PICKAXE
    );

    public static final List<Material> shovelItems = Arrays.asList(
            Material.WOODEN_SHOVEL,
            Material.STONE_SHOVEL,
            Material.IRON_SHOVEL,
            Material.DIAMOND_SHOVEL,
            Material.GOLDEN_SHOVEL,
            Material.NETHERITE_SHOVEL
    );

    public static final List<Material> hoeItems = Arrays.asList(
            Material.WOODEN_HOE,
            Material.STONE_HOE,
            Material.IRON_HOE,
            Material.DIAMOND_HOE,
            Material.GOLDEN_HOE,
            Material.NETHERITE_HOE
    );

    public static final List<Material> shearItems = Arrays.asList(
            Material.SHEARS
    );

    public static final List<Material> bowItems = Arrays.asList(
            Material.BOW,
            Material.CROSSBOW
    );

    public static final List<Material> foodItems = Arrays.asList(
            Material.APPLE,
            Material.BAKED_POTATO,
            Material.BEETROOT,
            Material.BEETROOT_SOUP,
            Material.BREAD,
            Material.CAKE,
            Material.CARROT,
            Material.CHORUS_FRUIT,
            Material.CARROT,
            Material.COOKED_CHICKEN,
            Material.COOKED_COD,
            Material.COOKED_MUTTON,
            Material.PORKCHOP,
            Material.RABBIT,
            Material.COOKED_SALMON,
            Material.COOKIE,
            Material.DRIED_KELP,
            Material.ENCHANTED_GOLDEN_APPLE,
            Material.GOLDEN_APPLE,
            Material.GLOW_BERRIES,
            Material.GOLDEN_CARROT,
            Material.HONEY_BOTTLE,
            Material.MELON_SLICE,
            Material.MUSHROOM_STEW,
            Material.POISONOUS_POTATO,
            Material.POTATO,
            Material.PUFFERFISH,
            Material.PUMPKIN_PIE,
            Material.BEEF,
            Material.CHICKEN,
            Material.COD,
            Material.MUTTON,
            Material.PORKCHOP,
            Material.RABBIT,
            Material.SALMON,
            Material.ROTTEN_FLESH,
            Material.SPIDER_EYE,
            Material.COOKED_BEEF,
            Material.SUSPICIOUS_STEW,
            Material.SWEET_BERRIES,
            Material.TROPICAL_FISH
    );

    public static final List<Material> potionItems = Arrays.asList(
            Material.POTION,
            Material.SPLASH_POTION,
            Material.LINGERING_POTION,
            Material.TIPPED_ARROW
    );

    public static final List<Material> fishingItems = List.of(
            Material.FISHING_ROD
    );

    public static final List<Material> elytraItems = List.of(
            Material.ELYTRA
    );

    public static final List<Material> shieldItems = List.of(
            Material.SHIELD
    );

    public static Map<List<Material>, ItemType> getMap() {

    Map<List<Material>, ItemType> itemMap = (Map.of(
            potionItems, ItemType.Potion,
            foodItems, ItemType.Food,
            bootsItems, ItemType.Boots,
            leggingsItems, ItemType.Leggings,
            chestplateItems, ItemType.Chestplate,
            helmetItems, ItemType.Helmet,
            shieldItems, ItemType.Shield

        ));
    Map<List<Material>, ItemType> itemMap2 = new java.util.HashMap<> (Map.of(
            fishingItems, ItemType.Fishing_Rod,
            axeItems, ItemType.Axe,
            pickaxeItems, ItemType.Pickaxe,
            shovelItems, ItemType.Shovel,
            hoeItems, ItemType.Hoe,
            swordItems, ItemType.Sword,
            bowItems, ItemType.Bow,
            shearItems, ItemType.Shears,
            foodItems, ItemType.Food,
            elytraItems, ItemType.Elytra
    ));
    itemMap2.putAll(itemMap);
    return itemMap2;
    }

    public static ItemType getItemType (Material material) {
        for (Map.Entry<List<Material>, ItemType> materialList : getMap().entrySet()) {
            if (materialList.getKey().contains(material)) {
                return materialList.getValue();
            }
        }
        return ItemType.Item;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public String getNameUpper() {
        return name().toUpperCase();
    }
}
