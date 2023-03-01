package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.Material;

public class Block extends ActionManager {

    public static void executePlace(Material mat, Location location) {
        location.getBlock().setType(mat);
    }

    public static void executeDestroy(Location location) {
        location.getBlock().setType(Material.AIR);
    }

    public static void executeBreak(Location location) {
        location.getBlock().breakNaturally();
    }
}
