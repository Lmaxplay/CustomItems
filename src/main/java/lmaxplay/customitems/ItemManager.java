package lmaxplay.customitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class ItemManager {
    private static HashMap<String, CustomItem> items = new HashMap<String, CustomItem>();
    private static HashMap<String, CustomItem> idMap = new HashMap<String, CustomItem>();

    /**
     * Registers an item inside the item manager class.
     * @param item
     */
    public static void register(CustomItem item) {
        items.put(item.getName(), item);
        idMap.put(item.getId(), item);

    }

    public static CustomItem getItem(String name) {
        try {
            return items.get(name);
        } catch (Exception e) {
            return null;
        }
    }

    public static CustomItem getItemById(String id) {
        try {
            return idMap.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<CustomItem> getItems() {
        return new ArrayList<CustomItem>(items.values());
    }
}
