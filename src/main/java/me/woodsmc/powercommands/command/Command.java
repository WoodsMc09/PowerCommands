package me.woodsmc.powercommands.command;

import me.woodsmc.powercommands.PowerCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.List;

public class Command extends BukkitCommand {

    private static final PowerCommands POWER_COMMANDS = PowerCommands.getPlugin(PowerCommands.class);
    public String name;

    //constructor
    public Command(String name) {
        super(name);
        this.description = "command";
    }

    //create a custom command
    public void createCommand(CommandMap commandMap, String name) {
        commandMap.register(name, new Command(name));
        this.name = name;

        List<String> list = POWER_COMMANDS.getCommandsYML().getConfig().getStringList("commands");
        list.add(name);
        POWER_COMMANDS.getCommandsYML().getConfig().set("commands", list);
        POWER_COMMANDS.getCommandsYML().saveConfig();
    }

    //custom command execute method
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Bukkit.getServer().getPluginManager().callEvent(new CustomCommandExecuteEvent(sender, "/" + commandLabel, args));
        return true;
    }

}