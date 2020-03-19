package com.joshy23.uncraftingtable.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class CommandBase implements TabExecutor {
    private InventoryOpenCommand invOpenCmd = new InventoryOpenCommand();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        invOpenCmd.onCommand(sender, command, label, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        invOpenCmd.onTabComplete(sender, command, label, args);
        return null;
    }
}
