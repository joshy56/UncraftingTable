package com.joshy23.uncraftingtable;

import com.joshy23.uncraftingtable.commands.CommandBase;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class UncraftingTable extends JavaPlugin {
    private static UncraftingTable plugin;

    public void onEnable() {
        plugin=this;
        getCommand("ut").setExecutor(new CommandBase());
        getCommand("ut").setTabCompleter(new CommandBase());
    }

    public void onDisable() {

    }

    public static UncraftingTable getPlugin() {
        return plugin;
    }

}
