package com.colin.teleportscrolls.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class LocationUtils {

    public static Location parseLocation(String input) {
        String[] args = input.split(":");
        return new Location(Bukkit.getWorld(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5]));
    }

    public static String parseString(Location loc) {
        return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
    }
}
