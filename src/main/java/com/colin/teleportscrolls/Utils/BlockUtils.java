package com.colin.teleportscrolls.Utils;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BlockUtils {
    public static List<Material> InteractableBlocks = new ArrayList<>(Arrays.asList(
            //Stations and Containers
            Material.CRAFTING_TABLE,
            Material.FURNACE,
            Material.ENCHANTING_TABLE,
            Material.ENDER_CHEST,
            Material.CHEST,
            Material.DROPPER,
            Material.SHULKER_BOX,
            Material.DISPENSER,
            Material.TRAPPED_CHEST,
            Material.HOPPER,
            Material.BREWING_STAND,
            Material.ANVIL,
            Material.CHIPPED_ANVIL,
            Material.DAMAGED_ANVIL,
            //Beds
            Material.BLACK_BED,
            Material.BLUE_BED,
            Material.YELLOW_BED,
            Material.BROWN_BED,
            Material.CYAN_BED,
            Material.GRAY_BED,
            Material.GREEN_BED,
            Material.LIGHT_BLUE_BED,
            Material.LIGHT_GRAY_BED,
            Material.LIME_BED,
            Material.MAGENTA_BED,
            Material.ORANGE_BED,
            Material.PINK_BED,
            Material.RED_BED,
            Material.WHITE_BED,
            Material.PURPLE_BED,
            //Doors
            Material.OAK_DOOR,
            Material.SPRUCE_DOOR,
            Material.BIRCH_DOOR,
            Material.JUNGLE_DOOR,
            Material.ACACIA_DOOR,
            Material.DARK_OAK_DOOR,
            //Trap Doors
            Material.OAK_TRAPDOOR,
            Material.SPRUCE_TRAPDOOR,
            Material.BIRCH_TRAPDOOR,
            Material.JUNGLE_TRAPDOOR,
            Material.ACACIA_TRAPDOOR,
            Material.DARK_OAK_TRAPDOOR,
            //Fence Gates
            Material.OAK_FENCE_GATE,
            Material.SPRUCE_FENCE_GATE,
            Material.BIRCH_FENCE_GATE,
            Material.JUNGLE_FENCE_GATE,
            Material.ACACIA_FENCE_GATE,
            Material.DARK_OAK_FENCE_GATE,
            //Buttons and Levers
            Material.OAK_BUTTON,
            Material.SPRUCE_BUTTON,
            Material.BIRCH_BUTTON,
            Material.JUNGLE_BUTTON,
            Material.ACACIA_BUTTON,
            Material.DARK_OAK_BUTTON,
            Material.STONE_BUTTON,
            Material.LEVER,
            //Redstone
            Material.COMPARATOR,
            Material.REPEATER,
            Material.DAYLIGHT_DETECTOR
            ));

    public static boolean isInteractableBlock(Material material) {
        return (InteractableBlocks.contains(material));
    }
}
