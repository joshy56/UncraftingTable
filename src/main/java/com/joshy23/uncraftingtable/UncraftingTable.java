package com.joshy23.uncraftingtable;

import com.joshy23.uncraftingtable.commands.CommandBase;
import com.joshy23.uncraftingtable.listener.InventoryListener;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class UncraftingTable extends JavaPlugin {
    private static UncraftingTable plugin;

    public void onEnable() {
        plugin=this;
        setCommands();
        setEvents();
    }

    public void onDisable() {

    }

    public static UncraftingTable getPlugin() {
        return plugin;
    }

    public void setCommands(){
        getCommand("ut").setExecutor(new CommandBase());
        getCommand("ut").setTabCompleter(new CommandBase());
    }

    public void setEvents(){
        getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
    }

}
