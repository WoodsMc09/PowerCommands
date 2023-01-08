package me.woodsmc.powercommands.actions;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ActionManager {

    //get all actions in a list
    public List<Class<?>> getActionLibrary(){
        //get reflections
        Reflections actions = new Reflections("me.woodsmc.powercommands.actions.actionlib");
        //make set of classes in the package me.woodsmc.powercommands.actions.actionlib
        Set<Class<? extends ActionManager>> actionClasses = actions.getSubTypesOf(ActionManager.class);

        //make list for classes
        List<Class<?>> classes = new ArrayList<>();
        //loop through actionclasses
        for(Class<?> c : actionClasses){
            //add classes to the list
            classes.add(c);
        }

        //return the list
        return classes;
    }
}
