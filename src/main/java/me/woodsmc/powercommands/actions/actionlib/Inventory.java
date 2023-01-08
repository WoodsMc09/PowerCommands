package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.entity.Player;

public class Inventory extends ActionManager {
    //inventory

    public static void executeOpenInventory(Player p, org.bukkit.inventory.Inventory inv){
        //open inventory
        p.openInventory(inv);
    }

    public static void executeCloseInventory(Player p){
        //close inventory
        p.closeInventory();
    }
}
