package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.Player;

public class Group extends ActionManager {
    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static void executeAddToGroup(Player p, String group) {
        pC.getActionsYML().getConfig().set(String.valueOf(p.getUniqueId()), group);
        pC.getActionsYML().saveConfig();
    }

    public static void executeRemoveFromGroup(Player p) {
        pC.getActionsYML().getConfig().set(String.valueOf(p.getUniqueId()), null);
        pC.getActionsYML().saveConfig();
    }
}
