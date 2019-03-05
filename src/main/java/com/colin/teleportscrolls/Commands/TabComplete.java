package com.colin.teleportscrolls.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args){
        List<String> options = new ArrayList<>();
        if(sender instanceof Player) {
            if(args.length > 0 && (sender.hasPermission("scrolls.*") || sender.hasPermission("*"))){
                switch(args.length) {
                    case 1: {
                        options.add("blank");
                        options.add("active");
                        break;
                    }
                    case 2: {
                        options.add("novice");
                        options.add("advanced");
                        options.add("master");
                        break;
                    }
                }
                return options;
            }
        }
        return options;
    }
}
