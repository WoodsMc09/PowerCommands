package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RunCommand extends ActionManager {
    //run command

    public static void executeRunCommand(Player p, String cmd){
        //run command from player
        p.performCommand(cmd.toLowerCase());
    }

    public static void executeRunCommand(String cmd){
        //run command from console
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd.toLowerCase());
    }

}
