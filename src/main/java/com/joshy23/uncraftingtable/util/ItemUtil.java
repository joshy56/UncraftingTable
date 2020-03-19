package com.joshy23.uncraftingtable.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemUtil {

    public static ItemStack createItem(String material, int amount){
        return new ItemStack(Material.getMaterial(material.toUpperCase()), amount);
    }

    public static ItemStack createItem(String material, int amount, int dataValue){
        ItemStack item = createItem(material, amount);
        item.setDurability((short) dataValue);
        return item;
    }

    public static ItemStack createItem(String material, int amount, int dataValue, String name){
        ItemStack item = createItem(material, amount, dataValue);
        ItemMeta meta = item.getItemMeta();
        if(name == null){
            meta.setDisplayName("&7 ");
        }else {
            meta.setDisplayName(ColorUtil.color(name));
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(String material, int amount, int dataValue, String name, List<String> lore){
        ItemStack item = createItem(material, amount, dataValue, name);
        ItemMeta meta = item.getItemMeta();
        if(lore == null){
            meta.setLore(null);
        }else {
            meta.setLore(ColorUtil.colors(lore));
        }
        item.setItemMeta(meta);
        return item;
    }

    public static List<ItemStack> getRecipes(ItemStack item){
        List<ItemStack> grid = new ArrayList<>();
        ItemStack ingredient;
        int count = 0;
        for(Recipe recipe:Bukkit.getRecipesFor(item)){
            if(recipe instanceof ShapelessRecipe){
                for(ItemStack itemStack:((ShapelessRecipe) recipe).getIngredientList()){
                    if(itemStack == null){
                        ingredient = new ItemStack(Material.AIR);
                    }else if(item.getDurability()>15){
                        ingredient = new ItemStack(itemStack.getType(), item.getAmount(), (short) 0);
                    }else{
                        ingredient = new ItemStack(itemStack.getType(), item.getAmount(), itemStack.getDurability());
                    }
                    grid.add(ingredient);
                }
            }else if(recipe instanceof ShapedRecipe){
                for(ItemStack itemStack:((ShapedRecipe) recipe).getIngredientMap().values()){
                    if(itemStack == null){
                        ingredient = new ItemStack(Material.AIR);
                    }else if(item.getDurability()>15){
                        ingredient = new ItemStack(itemStack.getType(), item.getAmount(), (short) 0);
                    }else{
                        ingredient = new ItemStack(itemStack.getType(), item.getAmount(), itemStack.getDurability());
                    }
                    grid.add(ingredient);
                }
            }
        }
        return grid;
    }

}
