package com.joshy23.uncraftingtable.listener;

import com.joshy23.uncraftingtable.commands.InventoryOpenCommand;
import com.joshy23.uncraftingtable.inventory.UncraftingInventory;
import com.joshy23.uncraftingtable.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getInventory().equals(UncraftingInventory.getInventory())) {
            Bukkit.getScheduler().cancelTask(InventoryOpenCommand.getTaskId());
            e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().firstEmpty(), e.getInventory().getItem(19));
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory clickedInv = e.getClickedInventory();
        List<ItemStack> ingredients = ItemUtil.getRecipes(clickedInv.getItem(19));
        if(!e.getSlotType().equals(InventoryType.SlotType.OUTSIDE)){
            if(e.getClickedInventory().equals(UncraftingInventory.getInventory())) {
                if (e.getSlot() == 49) {
                    e.getWhoClicked().closeInventory();
                }else if(e.getSlot() == 19){
                    e.setCancelled(false);
                }else if(InventoryOpenCommand.isReady()){
                    if(e.getSlot() == 25){
                        e.setCancelled(true);
                        for (int i = 0; i < ingredients.size(); i++) {
                            if(player.getInventory().contains(ingredients.get(i).getType())){
                                player.getInventory().setItem(player.getInventory().first(ingredients.get(i).getType()), new ItemStack(ingredients.get(i).getType(), ingredients.get(i).getAmount()+player.getInventory().getItem(player.getInventory().first(ingredients.get(i).getType())).getAmount(), ingredients.get(i).getDurability()));
                            }else{
                                player.getInventory().setItem(player.getInventory().firstEmpty(), ingredients.get(i));
                            }
                        }
                        e.getClickedInventory().setItem(19, new ItemStack(Material.AIR));
                    }
                }else{
                    e.setCancelled(true);
                }
            }else{
                e.setCancelled(false);
            }
        }
    }

}
