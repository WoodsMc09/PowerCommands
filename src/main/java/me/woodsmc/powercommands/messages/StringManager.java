package me.woodsmc.powercommands.messages;

import me.woodsmc.powercommands.PowerCommands;
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
    //get config messages
    public static String getConfigMessage(String msg){
        for(String s : pC.getConfig().getConfigurationSection("messages").getKeys(false)){
            if(s.equalsIgnoreCase(msg)){
                return formatColorCodes(pC.getConfig().getString("messages." + s));
            }
        }
        return null;
    }


    //format string config variables

    public static String cmd = "?CMD?";
    public static String console_use = "True";
    public static String perm = "perm.none";
    public static String formatConfigVariables(String s){
        s.replace("?CMD?", cmd).replace("?CON_USE?", console_use).replace("?PERM?", perm).replace("?PREFIX?", getPrefix());
        return s;
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
