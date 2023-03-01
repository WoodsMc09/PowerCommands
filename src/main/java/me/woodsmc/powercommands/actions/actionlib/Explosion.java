package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class Explosion extends ActionManager {

    public static void executeCreateExplosion(World w, Location location, float amp, boolean fire, boolean destroy) {
        w.createExplosion(location, amp, fire, destroy);
    }

    public static void executeCreateExplosion(World w, Location location, float amp, boolean fire, boolean destroy, Entity s) {
        w.createExplosion(location, amp, fire, destroy, s);
    }
}
