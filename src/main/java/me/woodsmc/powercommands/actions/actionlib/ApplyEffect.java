package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ApplyEffect extends ActionManager {
    //apply effect

    public static void executeApplyEffect(LivingEntity e, PotionEffectType type, int dur, int amp, boolean particles, boolean icon){
        //apply effecy
        e.addPotionEffect(new PotionEffect(type, dur*20, amp, false, particles, icon));
    }
}
