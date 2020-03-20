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
        for(Recipe recipe:Bukkit.getRecipesFor(item)){
            if(recipe instanceof ShapedRecipe){
                for(ItemStack ingredient:((ShapedRecipe) recipe).getIngredientMap().values()){
                    if(ingredient == null){
                        grid.add(new ItemStack(Material.AIR));
                    }else if(ingredient.getDurability()>15){
                        grid.add(new ItemStack(ingredient.getType(), item.getAmount(), (short) 0));
                    }else{
                        grid.add(new ItemStack(ingredient.getType(), item.getAmount(), ingredient.getDurability()));
                    }
                }
            }else if(recipe instanceof ShapelessRecipe){
                for(ItemStack ingredient:((ShapelessRecipe) recipe).getIngredientList()){
                    if(ingredient == null){
                        grid.add(new ItemStack(Material.AIR));
                    }else if(ingredient.getDurability()>15){
                        grid.add(new ItemStack(ingredient.getType(), item.getAmount(), (short) 0));
                    }else{
                        grid.add(new ItemStack(ingredient.getType(), item.getAmount(), ingredient.getDurability()));
                    }
                }
            }
        }
        return grid;
    }

}
