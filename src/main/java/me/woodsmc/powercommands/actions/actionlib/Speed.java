package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.Player;

public class Speed extends ActionManager {
    //speed

    public static void executeIncrementWalkingSpeed(Player p, float f){
        //increase walk speed
        p.setWalkSpeed(p.getWalkSpeed()+f);
    }

    public static void executeDecrementWalkingSpeed(Player p, float f){
        //decrease walk speed
        p.setWalkSpeed(p.getWalkSpeed()-f);
    }

    public static void executeSetWalkingSpeed(Player p, float f){
        //set walk speed
        p.setWalkSpeed(f);
    }

    public static void executeIncrementFlyingSpeed(Player p, float f){
        //increase fly speed
        p.setFlySpeed(p.getFlySpeed()+f);
    }

    public static void executeDecrementFlyingSpeed(Player p, float f){
        //decrease fly speed
        p.setFlySpeed(p.getFlySpeed()-f);
    }

    public static void executeSetFlyingSpeed(Player p, float f){
        //set fly speed
        p.setFlySpeed(f);
    }
}
