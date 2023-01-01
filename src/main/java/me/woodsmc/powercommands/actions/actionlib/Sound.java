package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Sound extends ActionManager {

    public static void executePlaySound(Player p, org.bukkit.Sound sound, float pitch, float vol){
        p.playSound(p.getLocation(), sound, vol, pitch);
    }
    public static void executePlaySound(Player p, Location location, org.bukkit.Sound sound, float pitch, float vol){
        p.playSound(location, sound, vol, pitch);
    }
}
