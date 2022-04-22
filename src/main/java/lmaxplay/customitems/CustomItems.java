package lmaxplay.customitems;

import lmaxplay.customitems.commands.ItemGiveCommand;
import lmaxplay.customitems.items.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CustomItems extends JavaPlugin {

    public static java.util.logging.Logger logger;

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        logger = getLogger();
        getServer().getPluginManager().registerEvents(new Listener(), this);
        // Register the tickloop
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new TickLoop(), 0, 1);

        Mana.scheduleManaRegen(this);

        // Register the commands
        Objects.requireNonNull(getCommand("customitem")).setExecutor(new ItemGiveCommand());

        // Register items
        ItemManager.register(new LaunchWand());
        ItemManager.register(new HealWand());
        ItemManager.register(new BoneSword());
        ItemManager.register(new BoneSwordRefined());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
