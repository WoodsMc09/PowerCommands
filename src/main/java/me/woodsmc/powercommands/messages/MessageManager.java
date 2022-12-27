package me.woodsmc.powercommands.messages;

import me.woodsmc.powercommands.PowerCommands;
import org.bukkit.ChatColor;

public class MessageManager {

    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static String getPrefix(){
        return formatColorCodes(pC.getConfig().getString("prefix"));
    }

    public static String getConfigMessage(String msg){
        for(String s : pC.getConfig().getConfigurationSection("messages").getKeys(false)){
            if(s.equalsIgnoreCase(msg)){
                return formatColorCodes(pC.getConfig().getString("messages." + s));
            }
        }
        return null;
    }





    private static String formatColorCodes(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
