package lmaxplay.customitems;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents the mana system.
 * @since 1.0
 */
public abstract class Mana {
    public static final double MAX_MANA = 100;
    public static final double MIN_MANA = 0;
    private static final HashMap<UUID, Double> playerMana = new HashMap<UUID, Double>();
    private static final HashMap<UUID, Double> playerMaxMana = new HashMap<UUID, Double>();

    /**
     * Adds mana to a player.
     * @param uuid The player's UUID.
     * @param amount The amount of mana to add.
     */
    public static void addMana(UUID uuid, double amount) {
        if (playerMana.containsKey(uuid)) {
            playerMana.put(uuid, playerMana.get(uuid) + amount);
        } else {
            playerMana.put(uuid, amount);
        }
    }

    /**
     * Increases the max mana of a player.
     * @param uuid The player's UUID.
     * @param amount The amount of max mana to add.
     */
    public static void addMaxMana(UUID uuid, double amount) {
        if (playerMaxMana.containsKey(uuid)) {
            playerMaxMana.put(uuid, playerMaxMana.get(uuid) + amount);
        } else {
            playerMaxMana.put(uuid, amount);
        }
    }

    /**
     * Sets the mana of a player.
     * @param uuid The player's UUID.
     * @param amount The amount of mana to set.
     */
    public static void setMana(UUID uuid, double amount) {
        if (playerMana.containsKey(uuid)) {
            playerMana.put(uuid, amount);
        } else {
            playerMana.put(uuid, amount);
        }
    }

    /**
     * Sets the max mana of a player.
     * @param uuid The player's UUID.
     * @param amount The amount of max mana to set.
     */
    public static void setMaxMana(UUID uuid, double amount) {
        if (playerMaxMana.containsKey(uuid)) {
            playerMaxMana.put(uuid, amount);
        } else {
            playerMaxMana.put(uuid, amount);
        }
    }

    /**
     * Gets the mana of a player.
     * @param uuid The player's UUID.
     * @return The mana of the player.
     */
    public static double getMana(UUID uuid) {
        return playerMana.getOrDefault(uuid, MIN_MANA);
    }

    /**
     * Gets the max mana of a player.
     * @param uuid The player's UUID.
     * @return The max mana of the player.
     */
    public static double getMaxMana(UUID uuid) {
        return playerMaxMana.getOrDefault(uuid, MAX_MANA);
    }

    /**
     * Removes mana from a player.
     * @param uuid The player's UUID.
     * @param amount The amount of mana to remove.
     */
    public static void removeMana(UUID uuid, double amount) {
        if (playerMana.containsKey(uuid)) {
            playerMana.put(uuid, playerMana.get(uuid) - amount);
            if(playerMana.get(uuid) - amount < MIN_MANA) {
                playerMana.put(uuid, MIN_MANA);
            }
        } else {
            playerMana.put(uuid, amount);
        }
    }

    /**
     * Removes max mana from a player.
     * @param uuid The player's UUID.
     * @param amount The amount of max mana to remove.
     */
    public static void removeMaxMana(UUID uuid, double amount) {
        if (playerMaxMana.containsKey(uuid)) {
            playerMaxMana.put(uuid, playerMaxMana.get(uuid) - amount);
        } else {
            playerMaxMana.put(uuid, amount);
        }
    }

    /**
     * Adds mana to a player.
     * @param player The player to add mana to.
     * @param amount The amount of mana to add.
     */
    public static void addMana(Player player, double amount) {
        addMana(player.getUniqueId(), amount);
    }

    /**
     * Adds max mana to a player.
     * @param player The player to add max mana to.
     * @param amount The amount of max mana to add.
     */
    public static void addMaxMana(Player player, double amount) {
        addMaxMana(player.getUniqueId(), amount);
    }

    /**
     * Sets the mana of a player.
     * @param player The player to set mana for.
     * @param amount The amount of mana to set.
     */
    public static void setMana(Player player, double amount) {
        setMana(player.getUniqueId(), amount);
    }

    /**
     * Sets the max mana of a player.
     * @param player The player to set max mana for.
     * @param amount The amount of max mana to set.
     */
    public static void setMaxMana(Player player, double amount) {
        setMaxMana(player.getUniqueId(), amount);
    }

    /**
     * Gets the mana of a player.
     * @param player The player to get mana for.
     * @return The mana of the player.
     */
    public static double getMana(Player player) {
        return getMana(player.getUniqueId());
    }

    /**
     * Gets the max mana of a player.
     * @param player The player to get max mana for.
     * @return The max mana of the player.
     */
    public static double getMaxMana(Player player) {
        return getMaxMana(player.getUniqueId());
    }

    /**
     * Removes mana from a player.
     * @param player The player to remove mana from.
     * @param amount The amount of mana to remove.
     */
    public static void removeMana(Player player, double amount) {
        removeMana(player.getUniqueId(), amount);
    }

    /**
     * Removes max mana from a player.
     * @param player The player to remove max mana from.
     * @param amount The amount of max mana to remove.
     */
    public static void removeMaxMana(Player player, double amount) {
        if (playerMaxMana.containsKey(player.getUniqueId())) {
            playerMaxMana.put(player.getUniqueId(), playerMaxMana.get(player.getUniqueId()) - amount);
        } else {
            playerMaxMana.put(player.getUniqueId(), amount);
        }
    }

    /**
     * Schedules a task to run every 4th of a second to update mana.
     * @param plugin
     */
    public static void scheduleManaRegen(JavaPlugin plugin) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (World world : plugin.getServer().getWorlds()) {
                for (Player player : world.getPlayers()) {
                    if (!playerMana.containsKey(player.getUniqueId())) {
                        playerMana.put(player.getUniqueId(), 0D);
                    }
                    if(!playerMaxMana.containsKey(player.getUniqueId())) {
                        playerMaxMana.put(player.getUniqueId(), MAX_MANA);
                    }
                    if(playerMana.get(player.getUniqueId()) > playerMaxMana.get(player.getUniqueId())) {
                        playerMana.put(player.getUniqueId(), playerMaxMana.get(player.getUniqueId()));
                    }
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.BLUE + "Mana: " + ChatColor.WHITE + playerMana.get(player.getUniqueId()) + "/" + playerMaxMana.get(player.getUniqueId())));
                }
            }
            for (Map.Entry<UUID, Double> entry : playerMana.entrySet()) {
                if (entry.getValue() < getMaxMana(entry.getKey())) {
                    setMana(entry.getKey(), Math.min(getMaxMana(entry.getKey()), (getMana(entry.getKey()) + (getMaxMana(entry.getKey()) / 50))));
                }
            }
        }, 0L, 5L);
    }
}
