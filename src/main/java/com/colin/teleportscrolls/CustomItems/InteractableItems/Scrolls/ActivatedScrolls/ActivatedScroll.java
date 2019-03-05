package com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.ActivatedScrolls;

import com.colin.teleportscrolls.CustomItems.InteractableItems.InteractableItem;
import com.colin.teleportscrolls.Utils.BlockUtils;
import com.colin.teleportscrolls.Utils.LocationUtils;
import com.colin.teleportscrolls.Utils.NBTUtils;
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

public abstract class ActivatedScroll extends InteractableItem {

    private int tier, uses;

    public ActivatedScroll(String id, ChatColor titleColor, String tierLabel, int tier, int uses) {
        super(Material.BOOK, "activated-scrolls", id);
        setDisplayName(titleColor + "Teleport Scroll");
        hideAttributes();
        addLore(ChatColor.GOLD + "Right Click To Teleport");
        addLore(ChatColor.GRAY + " - " + ChatColor.WHITE + "Tier" + ChatColor.GRAY + ": " + ChatColor.WHITE + tierLabel);
        setTier(tier);
        setUses(uses);
        embedUses(getUses());
        setGlow();
    }

    public void getClickBehavior(PlayerInteractEvent event, HeldIndex heldIndex, JavaPlugin plugin) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = getItemIndex(player, heldIndex);

        if(equals(item)) {
            if(event.getClickedBlock() != null && BlockUtils.isInteractableBlock(event.getClickedBlock().getType())) {
                return;
            }

            if(action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) {
                getTeleportBehavior(player, item, heldIndex);
            }
        }
    }

    public void getTeleportBehavior(Player player, ItemStack item, HeldIndex heldIndex) {
        Location teleportLocation = LocationUtils.parseLocation(NBTUtils.getNBTString(item, "location"));
        ItemStack itemNewStack = null;
        if (item.getAmount() > 1) {
            itemNewStack = new ItemStack(item);
            itemNewStack.setAmount(itemNewStack.getAmount() - 1);
            item.setAmount(1);
        }
        int uses = NBTUtils.getNBTInt(item, "uses");
        player.teleport(teleportLocation);
        if (uses >= 1) {
            uses--;
            if(uses > 0) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "Uses Remaining: " + uses));
            }
            setItemIndex(player, embedUses(uses, item), heldIndex);
        }
        if (itemNewStack != null) {
            player.getInventory().addItem(itemNewStack);
        }
    }

    public ItemStack getTeleportBehavior(Player player, ItemStack item) {
        Location teleportLocation = LocationUtils.parseLocation(NBTUtils.getNBTString(item, "location"));
        int uses = NBTUtils.getNBTInt(item, "uses");
        player.teleport(teleportLocation);
        if(uses >= 1) {
            uses--;
            return embedUses(uses, item);
        }
        else {
            return item;
        }
    }

    public int getTier() {
        return tier;
    }

    private int getUses() {
        return uses;
    }

    private void setTier(int tier) {
        this.tier = tier;
    }

    private void setUses(int uses) {
        this.uses = uses;
    }

    private ItemStack embedUses(int uses) {
        embedUses(uses, getItemStack());
        return getItemStack();
    }

    private ItemStack embedUses(int uses, ItemStack item) {
        setItemStack(NBTUtils.setNBTInt(item, "uses", uses));
        if(uses == -1) {
            setLoreLine(2, ChatColor.GRAY + " - " + ChatColor.WHITE + "Uses" + ChatColor.GRAY + ": " + ChatColor.WHITE + "Infinite");
        } else if (uses == 0){
            item.setAmount(0);
            return item;
        }
        else {
            setLoreLine(2, ChatColor.GRAY + " - " + ChatColor.WHITE + "Uses" + ChatColor.GRAY + ": " + ChatColor.WHITE + uses);
        }
        return getItemStack();
    }

    public ItemStack embedLocation(Location loc) {
        setItemStack(NBTUtils.setNBTString(getItemStack(), "location", LocationUtils.parseString(loc)));
        return getItemStack();
    }
}