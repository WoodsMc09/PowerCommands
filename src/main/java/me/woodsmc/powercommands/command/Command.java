package me.woodsmc.powercommands.command;

import me.woodsmc.powercommands.PowerCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.List;

public class Command extends BukkitCommand {

    //command name
    public String name;
    //main instance
    private static PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);


    //TODO make this functional

    //create a custom command
    public void createCommand(CommandMap commandMap, String name){
        //get commandmap and register new command
        commandMap.register(name, new Command(name));
        //command name is name
        this.name = name;

        //get all commands from commands.yml (not functional)
        List<String> list = pC.getCommandsYML().getConfig().getStringList("commands");
        //add the name to the list
        list.add(name);

        //set list to config
        pC.getCommandsYML().getConfig().set("commands", list);
        //save config
        pC.getCommandsYML().saveConfig();
    }

    //constructor
    public Command(String name){
        //super command name
        super(name);
        //not functional
        this.description = "command";
    }

    //custom command execute method
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        //call CustomCommandExecuteEvent
        Bukkit.getServer().getPluginManager().callEvent(new CustomCommandExecuteEvent(sender, "/" + commandLabel, args));
        return true;
    }
}
