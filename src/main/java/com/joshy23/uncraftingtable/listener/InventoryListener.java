package com.joshy23.uncraftingtable.listener;

import com.joshy23.uncraftingtable.commands.InventoryOpenCommand;
import com.joshy23.uncraftingtable.inventory.UncraftingInventory;
import com.joshy23.uncraftingtable.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getInventory().equals(UncraftingInventory.getInventory())) {
            Bukkit.getScheduler().cancelTask(InventoryOpenCommand.getTaskId());
            e.getPlayer().getInventory().setItem(e.getInventory().firstEmpty(), e.getInventory().getItem(21));
            if (InventoryOpenCommand.isReady()) {
                for (int i : UncraftingInventory.getGrid()) {
                    e.getPlayer().getInventory().setItem(e.getInventory().firstEmpty(), e.getInventory().getItem(i));
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(!e.getSlotType().equals(InventoryType.SlotType.OUTSIDE)){
            if(e.getClickedInventory().equals(UncraftingInventory.getInventory())) {
                e.setCancelled(true);
                if (e.getSlot() == 49) {
                    e.getWhoClicked().closeInventory();
                }else if(e.getSlot() == 21){
                    e.setCancelled(false);
                }
            }else{
                e.setCancelled(false);
            }
        }else{
            e.setCancelled(true);
        }
    }

}
