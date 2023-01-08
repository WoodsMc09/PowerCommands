package me.woodsmc.powercommands.command;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.inventory.InventoryManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.nio.Buffer;
import java.util.*;

public class CommandManager implements Listener {
    //main instance
    private final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);
    //inventory instance
    private final InventoryManager iM = new InventoryManager();


    private String name = "?CMD?";
    private boolean con_use = true;
    private String perm = "perm.none";

    //set the command name
    public void setCommandName(){
        this.name = pC.getCommandsYML().getConfig().getString("editing.name");
    }

    //change the command name
    public void changeCommandName(){
        //get editor of command
        Player editor = Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player"));
        //set editing name to true
        pC.getCommandsYML().getConfig().set("editing.changing-name", true);

        //close inventory
        editor.closeInventory();
        //send edit message
        editor.sendMessage(StringManager.getConfigMessage("change-command-name").replace("?PREFIX?", StringManager.getPrefix()));

        //save commands.yml config
        pC.getCommandsYML().saveConfig();
    }

    //set command permissions
    public void setCommandPerm(){
        this.perm = pC.getCommandsYML().getConfig().getString("editing.perm");
    }

    //change the command permissions
    public void changeCommandPerm(){
        //get editor of command
        Player editor = Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player"));
        //set editing permission to true
        pC.getCommandsYML().getConfig().set("editing.changing-perm", true);

        //close inventory
        editor.closeInventory();
        //send edit message
        editor.sendMessage(StringManager.getConfigMessage("change-command-perm").replace("?PREFIX?", StringManager.getPrefix()));

        //save commands.yml config
        pC.getCommandsYML().saveConfig();
    }

    //set console use
    public void setConsoleUse(boolean con_use){
        this.con_use = pC.getCommandsYML().getConfig().getBoolean("editing.con_use");
    }

    //change console use
    public void changeConsoleUse(){
        //check if console use is true
        if(pC.getCommandsYML().getConfig().getBoolean("editing.con_use")){
            //is true
            //set it to false
            con_use = false;

            //set editor
            setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")));
            //open creation inventory for command editor
            Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")).openInventory(iM.getInventory("creation"));
        }
        else{
            //is false
            //set it to true
            con_use = true;

            //set editor
            setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")));
            //open creation inventory for command editor
            Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")).openInventory(iM.getInventory("creation"));
        }
    }

    //format command message variables
    public String formatCommandVars(String s){
        //set command name
        setCommandName();

        //return variable formatted
        return s.replace("?CMD?", name).replace("?PERM?", pC.getCommandsYML().getConfig().getString("editing.perm")).replace("?CON_USE?", String.valueOf(pC.getCommandsYML().getConfig().getBoolean("editing.con_use")));
    }

    //format command list variables
    public List<String> formatCommandVars(List<String> list){
        //set command name
        setCommandName();

        //create list
        List<String> l = new ArrayList<>();
        //loop through list
        for(String s : list) {
            //add to list variable formatted strings
            l.add(s.replace("?CMD?", name).replace("?PERM?", pC.getCommandsYML().getConfig().getString("editing.perm")).replace("?CON_USE?", String.valueOf(pC.getCommandsYML().getConfig().getBoolean("editing.con_use"))));
        }

        //return variable formatted
        return l;
    }

    //set editing
    public void setEditing(String cmd, Player p){
        //set name of command
        pC.getCommandsYML().getConfig().set("editing.name", cmd);
        //set permission of command
        pC.getCommandsYML().getConfig().set("editing.perm", perm);
        //set console use of command
        pC.getCommandsYML().getConfig().set("editing.con_use", con_use);
        //set editor of command
        pC.getCommandsYML().getConfig().set("editing.player", p.getName());
        //set changing name of command
        pC.getCommandsYML().getConfig().set("editing.changing-name", false);
        //set changing perm of command
        pC.getCommandsYML().getConfig().set("editing.changing-perm", false);

        //save commands.yml config
        pC.getCommandsYML().saveConfig();
    }


    @EventHandler
    public void onPlayerAsyncChat(AsyncPlayerChatEvent event){
        //get player
        Player p = event.getPlayer();
        //check if player is editor
        if(p != Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")))
            return;
        //get event message
        String msg = event.getMessage();

        //TODO clean these if statements

        //check if changing name is true
        if(pC.getCommandsYML().getConfig().getBoolean("editing.changing-name")) {
            //check if message equals 'cancel-cmd'
            if (msg.equalsIgnoreCase("cancel-cmd")) {
                //wait
                Bukkit.getScheduler().runTaskLater(pC, () -> {
                    //open creation inventory
                    p.openInventory(iM.getInventory("creation"));
                }, 3);
                //reset editing
                setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), p);

                //cancel event
                event.setCancelled(true);
                return;
            }

            //set new name
            setEditing(msg.toLowerCase(), p);
            //wait
            Bukkit.getScheduler().runTaskLater(pC, () -> {
                //open creation inventory
                p.openInventory(iM.getInventory("creation"));
            }, 3);

            //cancel event
            event.setCancelled(true);
            return;
        }

        //check if changing permission is true
        if(pC.getCommandsYML().getConfig().getBoolean("editing.changing-perm")) {
            //check if message equals 'cancel-cmd'
            if (msg.equalsIgnoreCase("cancel-cmd")) {
                //wait
                Bukkit.getScheduler().runTaskLater(pC, () -> {
                    //open creation inventory
                    p.openInventory(iM.getInventory("creation"));
                }, 3);

                //reset editing
                setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), p);

                //cancel event
                event.setCancelled(true);
                return;
            }

            //set permissions
            this.perm = msg.toLowerCase();
            //set editing
            setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), p);

            //wait
            Bukkit.getScheduler().runTaskLater(pC, () -> {
                //open creation inventory
                p.openInventory(iM.getInventory("creation"));
            }, 3);

            //cancel event
            event.setCancelled(true);
            return;
        }

        //check if changing argument is true
        if(pC.getCommandsYML().getConfig().getBoolean("editing.changing-argument-name")) {
            //check if message equals 'cancel-cmd'
            if (msg.equalsIgnoreCase("cancel-cmd")) {
                //wait
                Bukkit.getScheduler().runTaskLater(pC, () -> {
                    //open argument-create inventory
                    p.openInventory(iM.getInventory("argument-create"));
                }, 3);

                //set editing
                setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), p);
                //cancel event
                event.setCancelled(true);
                return;
            }
            //TODO actually change the argument
            //set argument
            this.perm = msg.toLowerCase();
            //set editing
            setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), p);

            //wait
            Bukkit.getScheduler().runTaskLater(pC, () -> {
                //open argument-create inventory
                p.openInventory(iM.getInventory("argument-create"));
            }, 3);
            //cancel event
            event.setCancelled(true);
            return;
        }
    }

}
