package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.Material;

public class Block extends ActionManager {
    //block

    public static void executePlace(Material mat, Location location){
        //place block
        location.getBlock().setType(mat);
    }

    public static void executeDestroy(Location location){
        //destroy block
        location.getBlock().setType(Material.AIR);
    }

    public static void executeBreak(Location location){
        //break block
        location.getBlock().breakNaturally();
    }
}
