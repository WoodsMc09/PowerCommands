package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;


public class Teleport extends ActionManager {
    //teleport entity

    public static void executeTeleport(Entity e, Location location){
        e.teleport(location);
    }

    public static void executeTeleport(List<Entity> e, Location location){
        //loop through entities
        for(Entity entity : e) {
            //teleport them
            entity.teleport(location);
        }
    }
}
