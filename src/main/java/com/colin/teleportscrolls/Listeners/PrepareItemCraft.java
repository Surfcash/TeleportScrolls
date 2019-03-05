package com.colin.teleportscrolls.Listeners;

import com.colin.teleportscrolls.Recipes.*;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PrepareItemCraft implements Listener {

    @EventHandler
    public void onItemCraftEvent(PrepareItemCraftEvent event) {
        if(event.getInventory().getResult() == null || event.getInventory().getResult().getType() == Material.AIR) {
            return;
        }

        ShapelessCustomRecipe shapelessRecipe = ShapelessCustomRecipe.getRecipeByResult(event.getInventory().getResult());
        ShapedCustomRecipe shapedRecipe = ShapedCustomRecipe.getRecipeByResult(event.getInventory().getResult());

        if(shapelessRecipe != null) {
            ArrayList<ItemStack> ingredients = new ArrayList<>();
            for(int i = 1; i < 10; i++) {
                if(!(event.getInventory().getItem(i) == null || event.getInventory().getItem(i).getType() == Material.AIR)) {
                    ingredients.add(event.getInventory().getItem(i));
                }
            }
            if(!shapelessRecipe.equals(ingredients)) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
            }
        } else if(shapedRecipe != null) {
            ItemStack[] ingredients = new ItemStack[9];
            for(int i = 1; i < ingredients.length + 1; i++) {
                ingredients[i - 1] = event.getInventory().getItem(i);
            }

            if(!shapedRecipe.equals(ingredients)) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
    }
}
