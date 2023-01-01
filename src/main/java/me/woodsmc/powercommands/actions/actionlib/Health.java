package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Health extends ActionManager {

    public static void executeHeal(LivingEntity e, double d){
        e.setHealth(e.getHealth()+d);
    }

    public static void executeDamage(LivingEntity e, double d){
        e.damage(d);
    }

    public static void executeKill(LivingEntity e){
        e.setHealth(0);
    }

    public static void executeSaturate(Player p, int i){
        p.setFoodLevel(p.getFoodLevel()+i);
    }
}
