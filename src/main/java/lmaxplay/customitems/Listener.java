package lmaxplay.customitems;

import lmaxplay.customitems.helpers.ItemDrops;
import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;

import com.destroystokyo.paper.event.player.*;

import java.util.*;
import java.util.logging.Level;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
       }
    }
    // On entity damage by entity event
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        /*if (!(event.getEntity() instanceof LivingEntity)) {
        //    return;
        }*/
        if(event.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();

                if (player.getInventory().getItemInMainHand().getItemMeta() != null) {


                    List<String> lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();

                    if (lore == null) {
                        lore = new ArrayList<String>();
                    }

                    for (String line : lore) {
                        if (line.contains("Strength ")) {
                            String[] split = line.split(" ");
                            try {
                                // Parse roman numeral using standard library
                                Double strength = RomanNumeral.parseDouble(split[1]);
                                if (strength != null) {
                                    event.setDamage(event.getDamage() * (1 + (strength / 10)));
                                }
                            } catch (NumberFormatException e) {
                                continue;
                            }
                        }
                        for (Rarity rarity : Rarity.values()) {
                            if (line.contains(rarity.getName().toUpperCase() + " ")) {
                                event.setDamage(event.getDamage() * rarity.getMultiplier());
                            }
                        }
                    }
                }
            }
        }
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            double multiplier = 0, cost = 4;

            for (ItemStack armor : player.getInventory().getArmorContents()) {
                if (armor == null) continue;
                multiplier += Rarity.getRarity(armor).getMultiplier();
            }

            event.setDamage(event.getDamage() / (1 + (multiplier - cost) / 4));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraftItem(CraftItemEvent event) {
        Inventory inventory = event.getInventory();

        Player player = (Player) event.getWhoClicked();

        for (ItemStack item : inventory.getContents()) {

            if (item != null && item.hasItemMeta() &&
                    Objects.requireNonNull(item.getItemMeta()).hasDisplayName() && !item.getItemMeta().getDisplayName().equals("")) {

                event.setCancelled(true);
                // Anything extra here, perhaps send a message?
                player.sendMessage(ChatColor.RED + "You cannot craft items with custom names!");
                event.setResult(Event.Result.DENY);
                event.setCurrentItem(null);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlaceBlock(BlockPlaceEvent event) {
        if (event.getItemInHand().getItemMeta() == null) return;
        if (event.getItemInHand().getItemMeta().getDisplayName().equals("")) return;

        event.getPlayer().sendMessage(ChatColor.RED + "You cannot place items with custom names!");
        event.setCancelled(true);
    }

    // Inventoryclick event
    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() == null) return;
        if (!(event.getInventory().getHolder() instanceof Player)) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("")) return;
        // TODO: Something
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("")) return;

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            List<String> lore = event.getItem().getItemMeta().getLore();
            CustomItem item = ItemManager.getItem(event.getItem().getItemMeta().getDisplayName());
            if(item != null) {
                if (item.hasAbility()) {
                    item.use(event.getItem(), player);
                    if(!(event.getItem().getType().equals(Material.BOW)  || event.getItem().getType().equals(Material.CROSSBOW) || event.getItem().getType().equals(Material.FISHING_ROD) || event.getItem().getType().equals(Material.SHIELD))) {
                        event.setCancelled(true);
                    }
                }
            }
        }

    }

    @EventHandler(priority=EventPriority.LOWEST)
    public void onFurnaceCookEvent(FurnaceBurnEvent event) {
        if(event.getFuel().getItemMeta() == null) return;
        if(event.getFuel().getItemMeta().getDisplayName().equals("")) return;

        if(!event.getFuel().getItemMeta().getDisplayName().equals("")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onBowShot(EntityShootBowEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack item = event.getBow();
            if(Objects.requireNonNull(event.getBow()).getItemMeta() == null) return;
            if(event.getBow().getItemMeta().getDisplayName().equals("")) return;
            if(event.getProjectile() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setDamage(arrow.getDamage() * Rarity.getRarity(item).getMultiplier());
            }

            CustomItem customItem = ItemManager.getItem(event.getBow().getItemMeta().getDisplayName());
            if(customItem != null) {
                if(customItem.hasAbility()) {
                    customItem.shoot(event.getBow(), event.getProjectile(), player);
                }
            }
            event.setCancelled(false);
        }
    }
    // Entity death
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof Player) return;
        ItemDrops.drop(event.getEntity().getType(), event);
    }

    // On player crouch
    @EventHandler
    public void onPlayerCrouch(PlayerToggleSneakEvent event) {
        if(event.getPlayer().isSneaking()) {
            for (ItemStack item : event.getPlayer().getInventory().getArmorContents()) {
                CustomItem customItem = ItemManager.getItem(item.getItemMeta().getDisplayName());
                if (customItem != null) {
                    if (customItem.hasAbility()) {
                        customItem.crouch(item, event.getPlayer());
                    }
                }
            }
        }
    }
}
