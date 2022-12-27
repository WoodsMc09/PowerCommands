package me.woodsmc.powercommands;

import me.woodsmc.powercommands.actionslib.actions.Action;
import me.woodsmc.powercommands.actionslib.ActionYML;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PowerCommands extends JavaPlugin {

    private ActionYML actionYML;
    private Action action;

    @Override
    public void onEnable() {
        PluginManager pM = getServer().getPluginManager();
        //files
        actionYML = new ActionYML(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //action
        action = new Action();
        action.RegisterActions();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ActionYML getActionYML() {
        return actionYML;
    }
}
