package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Name extends ActionManager {

    public static void executeSetDisplayName(Player p, String name){
        p.setDisplayName(StringManager.formatColorCodes(name));
    }
    public static void executeSetDisplayName(Entity e, String name){
        e.setCustomName(StringManager.formatColorCodes(name));
        e.setCustomNameVisible(true);
    }
    public static void executeSetTabName(Player p, String name){
        p.setPlayerListName(StringManager.formatColorCodes(name));
    }
}
