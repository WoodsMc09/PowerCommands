package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

public class SummonEntity extends ActionManager {
    //summon entity

    public static void executeSummonEntity(EntityType e, double x, double y, double z, World w){
        //get location
        Location location = new Location(w, x, y, z);
        //summon entity
        w.spawnEntity(location, e);
    }
}
