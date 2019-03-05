package com.colin.teleportscrolls.Recipes;


import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class ShapelessCustomRecipe {

    // Static lists
    public static ArrayList<ShapelessCustomRecipe> recipes = new ArrayList<>();


    // Recipe variables
    public String name;
    public ArrayList<ItemStack> ingredients = new ArrayList<>();
    public ItemStack result;

    public ShapelessCustomRecipe(String name, ItemStack result) {
        this.name = name;
        this.result = new ItemStack(result);
        recipes.add(this);
    }

    public void addIngredient(int count, ItemStack item) {
        for(int i = 0; i < count; i++) {
            ingredients.add(item);
        }
    }

    public ShapelessRecipe getShapelessRecipe(JavaPlugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, name);
        ShapelessRecipe recipe = new ShapelessRecipe(key, result);

        for (ItemStack items : ingredients) {
            recipe.addIngredient(items.getType());
        }

        return recipe;
    }


    public static ShapelessCustomRecipe getRecipeByResult(ItemStack itemStack) {
        for (ShapelessCustomRecipe recipe : recipes)
            if (recipe.result.isSimilar(itemStack)) {
                return recipe;
            }

        return null;
    }

    public boolean equals(ArrayList<ItemStack> items) {
        if(items.size() != ingredients.size()) {
            return false;
        }
        boolean[] ignoreIndex = new boolean[ingredients.size()];
        for(int i = 0; i < items.size(); i++) {
            for(int j = 0; j < ingredients.size(); j++) {
                if(items.get(i) == null || items.get(j) == null) {
                    continue;
                }
                if(items.get(i).isSimilar(ingredients.get(j)) && !ignoreIndex[j]) {
                    ignoreIndex[j] = true;
                    break;
                }
            }
        }

        for(boolean index : ignoreIndex) {
            if(!index) {
                return false;
            }
        }
        return true;
    }
}