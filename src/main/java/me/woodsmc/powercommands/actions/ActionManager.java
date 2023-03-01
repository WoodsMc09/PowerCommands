package me.woodsmc.powercommands.actions;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ActionManager {

    public List<Class<?>> getActionsClasses() {
        Reflections actions = new Reflections("me.woodsmc.powercommands.actions.actionlib");
        Set<Class<? extends ActionManager>> actionClasses = actions.getSubTypesOf(ActionManager.class);
        return new ArrayList<>(actionClasses);
    }
}