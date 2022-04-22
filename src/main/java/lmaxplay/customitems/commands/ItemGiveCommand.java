package lmaxplay.customitems.commands;

import lmaxplay.customitems.ItemManager;
import org.bukkit.entity.*;
import org.bukkit.command.*;

import org.jetbrains.annotations.*;

public class ItemGiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) {
            sender.sendMessage("/customitem <player> <item>");
        }
        else if (args.length == 1) {
            if(sender instanceof Player) {
                try {
                    ((Player) sender).getInventory().addItem(ItemManager.getItemById(args[0]).createItemStack());
                } catch (Exception e) {
                    sender.sendMessage("Invalid item id " + args[0]);
                }
            }
        }
        return true;
    }
}
