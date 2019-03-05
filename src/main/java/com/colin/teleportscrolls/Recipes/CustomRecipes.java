package com.colin.teleportscrolls.Recipes;

import com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.BlankScrolls.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomRecipes {

    public static void addRecipes(JavaPlugin plugin) {
        initBlankNovice(plugin);
        initBlankAdvanced(plugin);
        initBlankMaster(plugin);
    }

    public static void initBlankNovice(JavaPlugin plugin) {
        ItemStack item = new NoviceBlankScroll().getItemStack();
        ShapedCustomRecipe recipe = new ShapedCustomRecipe("blank-scroll-novice", item);

        recipe.shape("EGE", "GBG", "EGE");

        recipe.setIngredient('B', new ItemStack(Material.BOOK,1));
        recipe.setIngredient('E', new ItemStack(Material.ENDER_PEARL,1));
        recipe.setIngredient('G', new ItemStack(Material.GLOWSTONE_DUST,1));

        Bukkit.addRecipe(recipe.getShapedRecipe(plugin));
    }

    public static void initBlankAdvanced(JavaPlugin plugin) {
        ItemStack item = new AdvancedBlankScroll().getItemStack();
        ShapedCustomRecipe recipe = new ShapedCustomRecipe("blank-scroll-advanced", item);

        recipe.shape("GEG", "EBE", "GEG");

        recipe.setIngredient('B', new NoviceBlankScroll().getItemStack());
        recipe.setIngredient('E', new ItemStack(Material.ENDER_PEARL,1));
        recipe.setIngredient('G', new ItemStack(Material.GHAST_TEAR,1));

        Bukkit.addRecipe(recipe.getShapedRecipe(plugin));
    }

    public static void initBlankMaster(JavaPlugin plugin) {
        ItemStack item = new MasterBlankScroll().getItemStack();
        ShapedCustomRecipe recipe = new ShapedCustomRecipe("blank-scroll-master", item);

        recipe.shape("DGD", "GBG", "DGD");

        recipe.setIngredient('B', new AdvancedBlankScroll().getItemStack());
        recipe.setIngredient('D', new ItemStack(Material.DIAMOND,1));
        recipe.setIngredient('G', new ItemStack(Material.GHAST_TEAR,1));

        Bukkit.addRecipe(recipe.getShapedRecipe(plugin));
    }
}