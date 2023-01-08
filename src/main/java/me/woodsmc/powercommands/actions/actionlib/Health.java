package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Health extends ActionManager {
    //health

    public static void executeHeal(LivingEntity e, double d){
        //heal entity
        e.setHealth(e.getHealth()+d);
    }

    public static void executeDamage(LivingEntity e, double d){
        //damage entity
        e.damage(d);
    }

    public static void executeKill(LivingEntity e){
        //kill entity
        e.setHealth(0);
    }

    public static void executeSaturate(Player p, int i){
        //saturate entity
        p.setFoodLevel(p.getFoodLevel()+i);
    }
}
