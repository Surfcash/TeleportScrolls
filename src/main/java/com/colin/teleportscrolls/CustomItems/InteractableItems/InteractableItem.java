package com.colin.teleportscrolls.CustomItems.InteractableItems;

import com.colin.teleportscrolls.CustomItems.CustomItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static com.colin.teleportscrolls.CustomItems.CustomItemUtils.getCustomItems;

public abstract class InteractableItem extends CustomItem {

    public enum HeldIndex {
        MAINHAND, OFFHAND;
    }

    /*+-----------
    ||CONSTRUCTORS
    ++------------*/
    public InteractableItem(Material material, String id) {
        super(material, id);
    }

    public InteractableItem(Material material, String rootId, String id) {
        super(material, rootId, id);
    }

    /*+------
    ||GETTERS
    ++-------*/
    public abstract void getClickBehavior(PlayerInteractEvent e, HeldIndex heldIndex, JavaPlugin plugin);

    public void getPlayerInteractBehavior(PlayerInteractEvent event, JavaPlugin plugin) {
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        if(equals(inv.getItemInMainHand())) {
            getClickBehavior(event, HeldIndex.MAINHAND, plugin);
        } else if(equals(inv.getItemInOffHand())) {
            getClickBehavior(event, HeldIndex.OFFHAND, plugin);
        }
    }

    public ItemStack getItemIndex(Player player, HeldIndex heldIndex) {
        PlayerInventory inv = player.getInventory();
        switch(heldIndex) {
            case MAINHAND: {
                return inv.getItemInMainHand();
            }
            case OFFHAND: {
                return inv.getItemInOffHand();
            }
            default : {
                return null;
            }
        }
    }

    public static ArrayList<InteractableItem> getInteractableItems() {
        ArrayList<InteractableItem> items = new ArrayList<>();
        for(CustomItem item : getCustomItems()) {
            if(item instanceof InteractableItem) {
                items.add((InteractableItem)item);
            }
        }
        return items;
    }

    /*+------
    ||SETTERS
    ++-------*/
    public void setItemIndex(Player player, ItemStack item, HeldIndex heldIndex) {
        switch(heldIndex) {
            case MAINHAND: {
                player.getInventory().setItemInMainHand(item);
                break;
            }
            case OFFHAND: {
                player.getInventory().setItemInOffHand(item);
            }
        }
    }
}