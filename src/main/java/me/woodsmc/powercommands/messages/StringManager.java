package me.woodsmc.powercommands.messages;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.command.CommandManager;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class StringManager {
    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);
    //main prefix
    public static String getPrefix(){
        return formatColorCodes(pC.getConfig().getString("prefix"));
    }
    //command manager instance
    private static final CommandManager cM = new CommandManager();
    //get config messages
    public static String getConfigMessage(String msg){
        for(String s : pC.getConfig().getConfigurationSection("messages").getKeys(false)){
            if(s.equalsIgnoreCase(msg)){
                return formatColorCodes(pC.getConfig().getString("messages." + s));
            }
        }
        return null;
    }


    //format string color codes
    public static String formatColorCodes(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    //format string list color codes
    public static List<String> formatListColorCodes(List<String> list){
        List<String> l = new ArrayList<>();
        for(String s : list){
            l.add(formatColorCodes(s));
        }
        return l;
    }
}
