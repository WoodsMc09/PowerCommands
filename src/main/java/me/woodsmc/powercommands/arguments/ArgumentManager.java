package me.woodsmc.powercommands.arguments;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ArgumentManager {
    //main instance
    private final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    //TODO make argument functional

    //set argument name
    public void setName(){
        //get command editor
        Player editor = Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player"));

        //close inventory
        editor.closeInventory();
        //send editing message
        editor.sendMessage(StringManager.getConfigMessage("change-command-arg"));

        //set argument editing to true
        pC.getCommandsYML().getConfig().set("editing.changing-argument-name", true);
        //save commands.yml config
        pC.getCommandsYML().saveConfig();
    }

}
