package lmaxplay.customitems.helpers;

import lmaxplay.customitems.CustomItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ItemDrops {

    private static final HashMap<EntityType, List<Drop>> dropTable = new HashMap<EntityType, List<Drop>>();

    public void addDrop(EntityType entity, Drop drop) {
        List<Drop> drops = dropTable.get(entity);
        if (drops == null) {
            drops = new ArrayList<>(1);
        }
        drops.add(drop);
        dropTable.put(entity, drops);
    }

    public static void drop(EntityType entity, EntityDeathEvent event) {
        List<Drop> dropList = dropTable.get(entity);
        List<ItemStack> drops = new ArrayList<ItemStack>();

        if (dropList != null) {
            for (Drop drop : dropList) {
                drop.drop();
            }
        }
    }

}
