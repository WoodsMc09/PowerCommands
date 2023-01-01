package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

public class SummonEntity extends ActionManager {

    public static void executeSummonEntity(EntityType e, double x, double y, double z, String w){
        Location location = new Location(Bukkit.getWorld(w.toLowerCase()), x, y, z);
        Bukkit.getWorld(w.toLowerCase()).spawnEntity(location, e);
    }
    public static void executeSummonEntity(EntityType e, double x, double y, double z, World w){
        Location location = new Location(w, x, y, z);
        w.spawnEntity(location, e);
    }
}
