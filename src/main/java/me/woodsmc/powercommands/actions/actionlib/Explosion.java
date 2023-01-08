package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class Explosion extends ActionManager {
    //explosion

    public static void executeCreateExplosion(World w, Location location, float amp, boolean fire, boolean destroy){
        //create explosion
        w.createExplosion(location, amp, fire, destroy);
    }

    public static void executeCreateExplosion(World w, Location location, float amp, boolean fire, boolean destroy, Entity s){
        //create explosion with entity ignite
        w.createExplosion(location, amp, fire, destroy, s);
    }
}
