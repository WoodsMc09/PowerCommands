package me.woodsmc.powercommands.messages;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.command.CommandManager;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class StringManager {

    private static final PowerCommands POWER_COMMANDS = PowerCommands.getPlugin(PowerCommands.class); // JavaPlugin instance
    private static final CommandManager COMMAND_MANAGER = new CommandManager(); // command manager instance

    //main prefix
    public static String getPrefix() {
        return formatColorCodes(POWER_COMMANDS.getConfig().getString("prefix"));
    }

    //get config messages
    public static String getConfigMessage(String msg) {
        for (String s : POWER_COMMANDS.getConfig().getConfigurationSection("messages").getKeys(false)) {
            if (s.equalsIgnoreCase(msg)) {
                return formatColorCodes(POWER_COMMANDS.getConfig().getString("messages." + s));
            }
        }
        return null;
    }

    // format string color codes
    public static String formatColorCodes(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // format string list color codes
    public static List<String> formatListColorCodes(List<String> list) {
        // todo use streams
        List<String> l = new ArrayList<>();
        for (String s : list) {
            l.add(formatColorCodes(s));
        }
        return l;
    }
}
