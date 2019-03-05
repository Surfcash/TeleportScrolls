package com.colin.teleportscrolls.Utils;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagInt;
import net.minecraft.server.v1_13_R2.NBTTagLong;
import net.minecraft.server.v1_13_R2.NBTTagString;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public final class NBTUtils {
    public static ItemStack setNBTString(ItemStack item, String tag, String value) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.set(tag, new NBTTagString(value));
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static ItemStack setNBTInt(ItemStack item, String tag, int value) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.set(tag, new NBTTagInt(value));
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static ItemStack setNBTLong(ItemStack item, String tag, long value) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.set(tag, new NBTTagLong(value));
        nmsStack.setTag(compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static String getNBTString(ItemStack item, String tag) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        return compound.getString(tag);
    }

    public static int getNBTInt(ItemStack item, String tag) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        return compound.getInt(tag);
    }

    public static long getNBTLong(ItemStack item, String tag) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        return compound.getLong(tag);
    }

    public static boolean hasNBTTag(ItemStack item, String tag) {
        net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        return compound.hasKey(tag);
    }

    public static boolean NBTCompare(ItemStack first, ItemStack second) {
        if (first == null || second == null) {
            return false;
        }
        net.minecraft.server.v1_13_R2.ItemStack nmsStackFirst = CraftItemStack.asNMSCopy(first);
        net.minecraft.server.v1_13_R2.ItemStack nmsStackSecond = CraftItemStack.asNMSCopy(second);
        return nmsStackFirst.getTag().toString().equals(nmsStackSecond.getTag().toString());
    }
}