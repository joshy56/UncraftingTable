package com.joshy23.uncraftingtable.commands;

import com.joshy23.uncraftingtable.inventory.UncraftingInventory;
import com.joshy23.uncraftingtable.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class InventoryOpenCommand implements TabExecutor {
    private Inventory gui;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            new UncraftingInventory(player);
            gui = UncraftingInventory.getInventory();
            player.openInventory(gui);
        }else{
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&cComando unicamente utilizable por un jugador"));
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
