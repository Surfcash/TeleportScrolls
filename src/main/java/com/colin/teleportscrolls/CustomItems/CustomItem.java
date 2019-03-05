package com.colin.teleportscrolls.CustomItems;

import com.colin.teleportscrolls.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomItem {

    /*+--------
    ||VARIABLES
    ++---------*/
    private String rootId = "custom-item";
    private String id;
    private ItemStack itemStack;

    /*+-----------
    ||CONSTRUCTORS
    ++------------*/
    public CustomItem() {
        this(Material.BARRIER, "custom-item");
    }

    public CustomItem(Material material, String id) {
        this.itemStack = new ItemStack(material);
        setId(id);
        embedId();
    }

    public CustomItem(Material material, String rootId, String id) {
        this.itemStack = new ItemStack(material);
        setId(id);
        setRootId(rootId);
        embedId();
    }

    public CustomItem(ItemStack item) {
        this();
        if (equals(item)) {
            setItemStack(item);
        }
    }

    /*+------
    ||GETTERS
    ++-------*/
    public String getRootId() {
        return rootId;
    }

    public String getId() {
        return id;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    /*+------
    ||SETTERS
    ++-------*/
    public void setRootId(String rootId) {
        this.rootId = getRootId() + "." + rootId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItemStack(ItemStack item) {
        this.itemStack = new ItemStack(item);
    }

    /*+------
    ||UTILITY
    ++-------*/
    private void embedId() {
        setItemStack(NBTUtils.setNBTString(getItemStack(), getRootId(), getId()));
    }

    public void setDisplayName(String displayName) {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.setDisplayName(displayName);
        setItemMeta(itemMeta);
    }

    public void setItemMeta(ItemMeta itemMeta) {
        ItemStack item = getItemStack();
        item.setItemMeta(itemMeta);
        setItemStack(item);
    }

    public ItemMeta getItemMeta() {
        return getItemStack().getItemMeta();
    }

    public void setLore(ArrayList<String> lore) {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

    public void setLoreLine(int line, String loreLine) {
        ItemMeta itemMeta = getItemMeta();
        List<String> lore = (itemMeta.hasLore()) ? itemMeta.getLore() : new ArrayList<>(4);
        if (lore.size() >= line + 1) {
            lore.set(line, loreLine);
        } else {
            lore.add(line, loreLine);
        }
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

    public void addLore(String loreLine) {
        ItemMeta itemMeta = getItemMeta();
        List<String> lore = (itemMeta.hasLore()) ? itemMeta.getLore() : new ArrayList<>(4);
        lore.add(loreLine);
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

    public void setGlow() {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, false);
        setItemMeta(itemMeta);
        hideEnchants();
    }

    public void hideEnchants() {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        setItemMeta(itemMeta);
    }

    public void hideAttributes() {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        setItemMeta(itemMeta);
    }

    public void embedDamageValue(int value) {
        ItemMeta itemMeta = getItemMeta();
        if (!(itemMeta instanceof Damageable)) {
            return;
        }
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        setItemMeta(itemMeta);
        Damageable dMeta = (Damageable) getItemMeta();
        dMeta.setDamage(value);
        setItemMeta((ItemMeta) dMeta);
    }

    /*+-------
    ||COMPARES
    ++--------*/
    public boolean hasRootId(ItemStack item) {
        return (NBTUtils.hasNBTTag(item, getRootId()));
    }

    public boolean hasId(ItemStack item) {
        return (hasRootId(item) && NBTUtils.getNBTString(item, getRootId()).equals(getId()));
    }

    /*+--------
    ||OVERRIDES
    ++---------*/

    public boolean equals(ItemStack item) {
        return (hasId(item));
    }

    public String toString() {
        return getRootId() + "." + getId();
    }
}
