package me.woodsmc.powercommands.actionslib.actions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;


public class Kill extends Action  {


    @Override
    public void executeAction(Entity e) {
        if(e == null)
            return;
        if(!(e instanceof LivingEntity))
            return;
        LivingEntity livingEntity = (LivingEntity) e;
        livingEntity.damage(livingEntity.getHealth()*2);
    }
}
