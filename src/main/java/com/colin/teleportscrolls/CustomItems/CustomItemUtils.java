package com.colin.teleportscrolls.CustomItems;

import com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.ActivatedScrolls.*;
import com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.BlankScrolls.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public final class CustomItemUtils {

    public static ArrayList<CustomItem> getCustomItems() {
        return new ArrayList<>(Arrays.asList(
                //Activated Scrolls
                new MasterActivatedScroll(),
                new AdvancedActivatedScroll(),
                new NoviceActivatedScroll(),

                //Blank Scrolls
                new MasterBlankScroll(),
                new AdvancedBlankScroll(),
                new NoviceBlankScroll()
        ));
    }

    public static CustomItem getCustomItem(ItemStack item) {
        for(CustomItem customItem : getCustomItems()) {
            if(customItem.equals(item)) {
                return customItem;
            }
        }
        return null;
    }

    public static CustomItem getCustomItem(String id) {
        for (CustomItem customItem : getCustomItems()) {
            if (customItem.getId().equals(id)) {
                return customItem;
            }
        }
        return null;
    }

    public static ItemStack getItemFromId(String id) {
        Material material = Material.matchMaterial(id);
        if(material != null) {
            return new ItemStack(material);
        }
        else {
            CustomItem customItem = CustomItemUtils.getCustomItem(id);
            if(customItem != null) {
                return customItem.getItemStack();
            }
        }
        return null;
    }

    public static boolean compareItem(ItemStack item1, ItemStack item2) {
        CustomItem customItem1 = getCustomItem(item1);
        CustomItem customItem2 = getCustomItem(item2);
        if(customItem1 != null || customItem2 != null) {
            if(customItem1 == null || customItem2 == null) {
                return false;
            } else {
                return customItem1.equals(customItem2.getItemStack());
            }
        } else {
            return item1.isSimilar(item2);
        }
    }
}