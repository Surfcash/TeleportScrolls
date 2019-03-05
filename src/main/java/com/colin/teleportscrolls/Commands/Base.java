package com.colin.teleportscrolls.Commands;

import com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.ActivatedScrolls.*;
import com.colin.teleportscrolls.CustomItems.InteractableItems.Scrolls.BlankScrolls.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Base implements CommandExecutor {

    private JavaPlugin plugin;

    public Base(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length >= 1 && player.hasPermission("scrolls.*")) {
                switch(args[0]) {
                    case "blank" : {
                        if(args.length >= 2) {
                            switch(args[1]) {
                                case "novice" : {
                                    player.getInventory().addItem(new NoviceBlankScroll().getItemStack());
                                    break;
                                }
                                case "advanced" : {
                                    player.getInventory().addItem(new AdvancedBlankScroll().getItemStack());
                                    break;
                                }
                                case "master" : {
                                    player.getInventory().addItem(new MasterBlankScroll().getItemStack());
                                }
                            }
                        }
                        break;
                    }
                    case "active" : {
                        if(args.length >= 2) {
                            switch(args[1]) {
                                case "novice" : {
                                    player.getInventory().addItem(new NoviceActivatedScroll().embedLocation(player.getLocation()));
                                    break;
                                }
                                case "advanced" : {
                                    player.getInventory().addItem(new AdvancedActivatedScroll().embedLocation(player.getLocation()));
                                    break;
                                }
                                case "master" : {
                                    player.getInventory().addItem(new MasterActivatedScroll().embedLocation(player.getLocation()));
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }
}