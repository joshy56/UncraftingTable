package com.joshy23.uncraftingtable.inventory;

import com.joshy23.uncraftingtable.util.ColorUtil;
import com.joshy23.uncraftingtable.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UncraftingInventory {
    private static Inventory inventory;
    private final int[] grid = {14,15,16,23,24,25,32,33,34};

    public UncraftingInventory(Player player){
        inventory = Bukkit.createInventory(player, 54, ColorUtil.color("&2&m<<&3&lUncraftingTable&2&m>>"));
        for (int i = 0; i < inventory.getSize()-1; i++) {
            inventory.setItem(i, ItemUtil.createItem("stained_glass_pane", 1, 7, " "));
        }
        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, ItemUtil.createItem("stained_glass_pane", 1, 14, " "));
        }
        for(int i:grid){
            inventory.setItem(i, new ItemStack(Material.BARRIER, 1));
        }
        inventory.setItem(21, new ItemStack(Material.AIR, 1));
        inventory.setItem(49, ItemUtil.createItem("barrier", 1, 0, "&c&lCERRAR"));
    }

    public static void setInventory(Inventory gui) {
        UncraftingInventory.inventory = gui;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public int[] getGrid() {
        return grid;
    }

}
