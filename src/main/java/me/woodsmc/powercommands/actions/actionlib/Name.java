package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Name extends ActionManager {
    //name

    public static void executeSetDisplayName(Player p, String name){
        //set display name
        p.setDisplayName(StringManager.formatColorCodes(name));
    }

    public static void executeSetDisplayName(Entity e, String name){
        //set mob display name
        e.setCustomName(StringManager.formatColorCodes(name));
        e.setCustomNameVisible(true);
    }

    public static void executeSetTabName(Player p, String name){
        //set tablist name
        p.setPlayerListName(StringManager.formatColorCodes(name));
    }

    public static void executeResetDisplayName(Player p, String name){
        //reset display name
        p.setDisplayName(p.getName());
    }

    public static void executeResetDisplayName(Entity e, String name){
        //reset mob display name
        e.setCustomName(null);
        e.setCustomNameVisible(false);
    }

    public static void executeResetTabName(Player p, String name){
        //reset tablist name
        p.setPlayerListName(p.getName());
    }
}
