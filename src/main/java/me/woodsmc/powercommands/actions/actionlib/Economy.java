package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.OfflinePlayer;

public class Economy extends ActionManager {
    //economy (VaultAPI)

    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static void executeSetBalance(OfflinePlayer p, double d){
        //get balance
        double balance = pC.getEconomy().getBalance(p);
        //get difference
        double difference = balance - d;
        //check difference
        if(difference > 0){
            //set balance
            pC.getEconomy().depositPlayer(p, difference);
        }
        else{
            //set balance
            pC.getEconomy().withdrawPlayer(p, -difference);
        }
    }

    public static void executeDeposit(OfflinePlayer p, double d){
        //deposit
        pC.getEconomy().depositPlayer(p, d);
    }

    public static void executeWithdraw(OfflinePlayer p, double d){
        //withdraw
        pC.getEconomy().withdrawPlayer(p, d);
    }
}
