package com.joshy23.uncraftingtable.commands;

import com.joshy23.uncraftingtable.UncraftingTable;
import com.joshy23.uncraftingtable.inventory.UncraftingInventory;
import com.joshy23.uncraftingtable.util.ColorUtil;
import com.joshy23.uncraftingtable.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class InventoryOpenCommand implements TabExecutor {
    private UncraftingTable plugin = UncraftingTable.getPlugin();
    private UncraftingInventory inventory;
    private Inventory gui;
    private static int taskId;
    private static boolean isReady = false;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length>1 && args[0].equalsIgnoreCase("open")) {
                Player player = (Player) sender;
                inventory = new UncraftingInventory(player);
                gui = UncraftingInventory.getInventory();
                player.openInventory(gui);
                taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        if(gui.getItem(21) == null){
                            setReady(false);
                            inventory.setWaiting();
                        }else{
                            if(ItemUtil.getRecipes(gui.getItem(21)).isEmpty()){

                            }else{
                                inventory.setReady();
                                setReady(true);
                                for(ItemStack item:ItemUtil.getRecipes(gui.getItem(21))){
                                    for(int i:UncraftingInventory.getGrid()){
                                        gui.setItem(i, item);
                                    }
                                }
                            }
                        }
                        player.updateInventory();
                    }
                },0L, 1L);
            }
        }else{
            Bukkit.getConsoleSender().sendMessage(ColorUtil.color("&cComando unicamente utilizable por un jugador"));
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    public static int getTaskId() {
        return taskId;
    }

    public static boolean isReady() {
        return isReady;
    }

    public static void setReady(boolean ready) {
        isReady = ready;
    }

}
