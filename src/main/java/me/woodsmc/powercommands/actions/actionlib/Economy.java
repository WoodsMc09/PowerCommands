package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.OfflinePlayer;

public class Economy extends ActionManager {
    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static void executeSetBalance(OfflinePlayer p, double d){
        double balance = pC.getEconomy().getBalance(p);
        double difference = balance - d;
        if(difference > 0){
            pC.getEconomy().depositPlayer(p, difference);
        }
        else{
            pC.getEconomy().withdrawPlayer(p, -difference);
        }
    }

    public static void executeDeposit(OfflinePlayer p, double d){
        pC.getEconomy().depositPlayer(p, d);
    }

    public static void executeWithdraw(OfflinePlayer p, double d){
        pC.getEconomy().withdrawPlayer(p, d);
    }
}
