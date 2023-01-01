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
    private final InventoryManager iM = new InventoryManager();
    private String name = "?CMD?";
    private boolean con_use = true;
    private String perm = "perm.none";

    public void setCommandName(){
        this.name = pC.getCommandsYML().getConfig().getString("editing.name");
    }

    public void changeCommandName(){
        Player editor = Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player"));
        pC.getCommandsYML().getConfig().set("editing.changing-name", true);
        editor.closeInventory();
        editor.sendMessage(StringManager.getConfigMessage("change-command-name").replace("?PREFIX?", StringManager.getPrefix()));
        pC.getCommandsYML().saveConfig();
    }

    public void setCommandPerm(){
        this.perm = pC.getCommandsYML().getConfig().getString("editing.perm");
    }

    public void changeCommandPerm(){
        Player editor = Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player"));
        pC.getCommandsYML().getConfig().set("editing.changing-perm", true);
        editor.closeInventory();
        editor.sendMessage(StringManager.getConfigMessage("change-command-perm").replace("?PREFIX?", StringManager.getPrefix()));
        pC.getCommandsYML().saveConfig();
    }

    public void setConsoleUse(boolean con_use){
        this.con_use = pC.getCommandsYML().getConfig().getBoolean("editing.con_use");
    }

    public void changeConsoleUse(){
        if(pC.getCommandsYML().getConfig().getBoolean("editing.con_use")){
            con_use = false;
            setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")));
            Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")).openInventory(iM.getInventory("creation"));
        }
        else{
            con_use = true;
            setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")));
            Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")).openInventory(iM.getInventory("creation"));
        }
    }

    public String formatCommandVars(String s){
        setCommandName();
        return s.replace("?CMD?", name).replace("?PERM?", pC.getCommandsYML().getConfig().getString("editing.perm")).replace("?CON_USE?", String.valueOf(pC.getCommandsYML().getConfig().getBoolean("editing.con_use")));
    }

    public List<String> formatCommandVars(List<String> list){
        setCommandName();
        List<String> l = new ArrayList<>();
        for(String s : list) {
            l.add(s.replace("?CMD?", name).replace("?PERM?", pC.getCommandsYML().getConfig().getString("editing.perm")).replace("?CON_USE?", String.valueOf(pC.getCommandsYML().getConfig().getBoolean("editing.con_use"))));
        }
        return l;
    }

    public void setEditing(String cmd, Player p){
        pC.getCommandsYML().getConfig().set("editing.name", cmd);
        pC.getCommandsYML().getConfig().set("editing.perm", perm);
        pC.getCommandsYML().getConfig().set("editing.con_use", con_use);
        pC.getCommandsYML().getConfig().set("editing.player", p.getName());
        pC.getCommandsYML().getConfig().set("editing.changing-name", false);
        pC.getCommandsYML().getConfig().set("editing.changing-perm", false);
        pC.getCommandsYML().saveConfig();
    }


    @EventHandler
    public void onPlayerAsyncChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if(p != Bukkit.getPlayerExact(pC.getCommandsYML().getConfig().getString("editing.player")))
            return;
        String msg = event.getMessage();
        if(pC.getCommandsYML().getConfig().getBoolean("editing.changing-name")) {
            if (msg.equalsIgnoreCase("cancel-cmd")) {
                Bukkit.getScheduler().runTaskLater(pC, () -> {
                    p.openInventory(iM.getInventory("creation"));
                }, 3);
                pC.getCommandsYML().getConfig().set("editing.changing-name", false);
                pC.getCommandsYML().saveConfig();
                event.setCancelled(true);
                return;
            }
            setEditing(msg.toLowerCase(), p);
            Bukkit.getScheduler().runTaskLater(pC, () -> {
                p.openInventory(iM.getInventory("creation"));
            }, 3);
            event.setCancelled(true);
            return;
        }
        if(pC.getCommandsYML().getConfig().getBoolean("editing.changing-perm")) {
            if (msg.equalsIgnoreCase("cancel-cmd")) {
                Bukkit.getScheduler().runTaskLater(pC, () -> {
                    p.openInventory(iM.getInventory("creation"));
                }, 3);
                pC.getCommandsYML().getConfig().set("editing.changing-perm", false);
                pC.getCommandsYML().saveConfig();
                event.setCancelled(true);
                return;
            }
            this.perm = msg.toLowerCase();
            setEditing(pC.getCommandsYML().getConfig().getString("editing.name"), p);
            Bukkit.getScheduler().runTaskLater(pC, () -> {
                p.openInventory(iM.getInventory("creation"));
            }, 3);
            event.setCancelled(true);
            return;
        }
    }

}
