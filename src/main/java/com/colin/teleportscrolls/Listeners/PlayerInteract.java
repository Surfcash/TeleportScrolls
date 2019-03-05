package com.colin.teleportscrolls.Listeners;

import com.colin.teleportscrolls.CustomItems.CustomItem;
import com.colin.teleportscrolls.CustomItems.CustomItemUtils;
import com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.ActivatedScrolls.ActivatedScroll;
import com.colin.teleportscrolls.CustomItems.InteractableItems.InteractableItem;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class PlayerInteract implements Listener {

    private final JavaPlugin plugin;

    public PlayerInteract(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInv = player.getInventory();
        ArrayList<ItemStack> heldItems = new ArrayList<>();
        heldItems.add(playerInv.getItemInMainHand());
        heldItems.add(playerInv.getItemInOffHand());

        for(ItemStack item : heldItems) {
            CustomItem customItem = CustomItemUtils.getCustomItem(item);
            if(customItem instanceof InteractableItem) {
                InteractableItem interactableItem = (InteractableItem)customItem;
                interactableItem.getPlayerInteractBehavior(event, plugin);
            }
        }
    }

    @EventHandler
    public void onPlayerClickEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if(event.getRightClicked() instanceof ItemFrame) {
            ItemFrame itemFrame = (ItemFrame)event.getRightClicked();
            ItemStack item = itemFrame.getItem();
            CustomItem customItem = CustomItemUtils.getCustomItem(item);
            if(customItem instanceof ActivatedScroll) {
                ActivatedScroll scroll = (ActivatedScroll)customItem;
                itemFrame.setItem(scroll.getTeleportBehavior(player, item));
                event.setCancelled(true);
            }
        }
    }
}