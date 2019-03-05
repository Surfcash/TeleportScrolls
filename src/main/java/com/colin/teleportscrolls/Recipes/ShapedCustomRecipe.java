package com.colin.teleportscrolls.Recipes;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ShapedCustomRecipe {

    // Static lists
    public static ArrayList<ShapedCustomRecipe> recipes = new ArrayList<>();


    // Recipe variables
    public String name;
    public String[] shape = new String[3];
    public HashMap<Character, ItemStack> ingredients = new HashMap<>();
    public ItemStack result;

    public ShapedCustomRecipe(String name, ItemStack result) {
        this.name = name;
        this.result = new ItemStack(result);
        recipes.add(this);
    }

    public void shape(String row_one, String row_two, String row_three) {
        shape = new String[]{row_one, row_two, row_three};
    }

    public void setIngredient(char key, ItemStack item) {
        ingredients.put(key, item);
    }

    public ShapedRecipe getShapedRecipe(JavaPlugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, name);
        ShapedRecipe recipe = new ShapedRecipe(key, result);

        recipe.shape(shape[0], shape[1], shape[2]);
        for(Map.Entry<Character, ItemStack> e : ingredients.entrySet()) {
            recipe.setIngredient(e.getKey(), e.getValue().getType());
        }

        return recipe;
    }


    public static ShapedCustomRecipe getRecipeByResult(ItemStack itemStack) {
        for (ShapedCustomRecipe recipe : recipes)
            if (recipe.result.isSimilar(itemStack)) {
                return recipe;
            }

        return null;
    }

    public boolean equals(ItemStack[] items) {
        ItemStack[] ingredientsAsArray = new ItemStack[9];
        char[] recipeShape = (shape[0] + shape[1] + shape[2]).toCharArray();
        for(int i = 0; i < ingredientsAsArray.length; i++) {
            ingredientsAsArray[i] = ingredients.get(recipeShape[i]);
        }

        for(int i = 0; i < ingredientsAsArray.length; i++) {
            if(items[i] == null || ingredientsAsArray[i] == null) {
                continue;
            }
            if(!items[i].isSimilar(ingredientsAsArray[i])) {
                return false;
            }
        }
        return true;
    }
}