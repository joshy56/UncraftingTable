package com.joshy23.uncraftingtable.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ColorUtil {

    public static String color(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> colors(List<String> texts){
        List<String> results = new ArrayList<>();
        for (String text:texts) {
            results.add(color(text));
        }
        return results;
    }

}
