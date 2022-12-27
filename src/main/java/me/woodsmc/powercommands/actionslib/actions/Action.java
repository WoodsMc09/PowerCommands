package me.woodsmc.powercommands.actionslib.actions;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actionslib.ActionExecuter;
import org.bukkit.entity.Entity;
import org.reflections.Reflections;

import java.util.Set;
import java.util.logging.Level;

public class Action implements ActionExecuter {
    private final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public void RegisterActions(){
        if(pC.getActionYML().getConfig().getBoolean("registered")){
            pC.getServer().getLogger().log(Level.INFO, "Command Actions: registered");
            return;
        }
        Reflections reflections = new Reflections("me.woodsmc.powercommands.actionslib.actions");
        Set<Class<? extends Action>> classes = reflections.getSubTypesOf(Action.class);
        for(Class<? extends Action> actionClasses : classes){
            pC.getActionYML().getConfig().set(actionClasses.getName(), "registered");
            pC.getActionYML().saveConfig();
            pC.getServer().getLogger().log(Level.INFO, "Command Actions: registered " + actionClasses.getName());
        }

    }


    @Override
    public void executeAction(){

    }

    @Override
    public void executeAction(Entity e){

    }
}
