package com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.BlankScrolls;

import com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.ActivatedScrolls.ActivatedScroll;
import com.colin.teleportscrolls.CustomItems.InteractableItems.InteractableItem;
import com.colin.teleportscrolls.CustomItems.*;
import com.colin.teleportscrolls.Utils.BlockUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BlankScroll extends InteractableItem {

    private int tier;

    public BlankScroll(String id, ChatColor titleColor, String tierLabel, int tier) {
        super(Material.BOOK, "blank-scrolls", id);
        setDisplayName(titleColor + "Blank Scroll");
        addLore(ChatColor.GOLD + "Right Click To Set Teleport Location");
        addLore(ChatColor.GRAY + " - " + ChatColor.WHITE + "Tier" + ChatColor.GRAY + ": " + ChatColor.WHITE + tierLabel);
        setTier(tier);
    }

    public void getClickBehavior(PlayerInteractEvent event, HeldIndex heldIndex, JavaPlugin plugin) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = getItemIndex(player, heldIndex);
        Location location = player.getLocation();
        if(equals(item)) {
            if(event.getClickedBlock() != null && BlockUtils.isInteractableBlock(event.getClickedBlock().getType())) {
                return;
            }
            if(action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) {
                for(CustomItem customItem : CustomItemUtils.getCustomItems()) {
                    if(customItem instanceof ActivatedScroll) {
                        ActivatedScroll activatedScroll = (ActivatedScroll) customItem;
                        if(activatedScroll.getTier() != getTier()) {
                            continue;
                        }
                        ItemStack newItem = activatedScroll.embedLocation(location);
                        if(item.getAmount() > 1) {
                            item.setAmount(item.getAmount() - 1);
                            player.getInventory().addItem(newItem);
                        } else {
                            setItemIndex(player, newItem, heldIndex);
                        }
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "Teleport Scroll Activated"));
                    }
                }
            }
        }
    }

    private int getTier() {
        return tier;
    }

    private void setTier(int tier) {
        this.tier = tier;
    }
}