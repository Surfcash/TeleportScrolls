package com.colin.teleportscrolls;

import com.colin.teleportscrolls.Recipes.CustomRecipes;
import org.bukkit.plugin.java.JavaPlugin;
import com.colin.teleportscrolls.Listeners.*;
import com.colin.teleportscrolls.Commands.*;

public final class TeleportScrolls extends JavaPlugin {

    @Override
    public void onEnable() {
        CustomRecipes.addRecipes(this);

        this.getCommand("scrolls").setExecutor(new Base(this));
        this.getCommand("scrolls").setTabCompleter(new TabComplete());

        getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new PrepareItemCraft(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
