package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.Player;

public class Speed extends ActionManager {

    public static void executeIncrementWalkingSpeed(Player p, float f) {
        p.setWalkSpeed(p.getWalkSpeed() + f);
    }

    public static void executeDecrementWalkingSpeed(Player p, float f) {
        p.setWalkSpeed(p.getWalkSpeed() - f);
    }

    public static void executeSetWalkingSpeed(Player p, float f) {
        p.setWalkSpeed(f);
    }

    public static void executeIncrementFlyingSpeed(Player p, float f) {
        p.setFlySpeed(p.getFlySpeed() + f);
    }

    public static void executeDecrementFlyingSpeed(Player p, float f) {
        p.setFlySpeed(p.getFlySpeed() - f);
    }

    public static void executeSetFlyingSpeed(Player p, float f) {
        p.setFlySpeed(f);
    }
}
