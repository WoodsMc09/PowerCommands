package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.Player;

public class Experience extends ActionManager {

    public static void executeGiveEXP(Player p, float f){
        p.setExp(p.getExp()+f);
    }

    public static void executeGiveEXPLevel(Player p, int i){
        p.setLevel(p.getLevel()+i);
    }

    public static void executeTakeEXP(Player p, float f){
        p.setExp(p.getExp()-f);
    }

    public static void executeTakeEXPLevel(Player p, int i){
        p.setLevel(p.getLevel()-i);
    }

    public static void executeSetEXP(Player p, float f){
        p.setExp(f);
    }

    public static void executeSetEXPLevel(Player p, int i){
        p.setLevel(i);
    }
}
