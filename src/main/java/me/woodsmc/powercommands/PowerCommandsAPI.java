package me.woodsmc.powercommands;


import me.woodsmc.powercommands.actions.ActionManager;

public class PowerCommandsAPI {

    private ActionManager aM;

    public PowerCommandsAPI(){
        aM = new ActionManager();
    }

    public ActionManager getActionManager(){
        return aM;
    }

}
