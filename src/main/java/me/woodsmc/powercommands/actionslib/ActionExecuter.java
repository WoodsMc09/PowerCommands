package me.woodsmc.powercommands.actionslib;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.entity.Entity;

public interface ActionExecuter {

    void executeAction();
    void executeAction(Entity e);
}
