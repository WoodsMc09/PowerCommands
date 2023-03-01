package me.woodsmc.powercommands.command;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.inventory.InventoryManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements Listener {
    //main instance
    private final PowerCommands powerCommands = PowerCommands.getPlugin(PowerCommands.class);
    private final InventoryManager inventoryManager = new InventoryManager();
    private boolean con_use = true;
    private String name = "?CMD?", perm = "perm.none";

    public void setCommandName() {
        this.name = powerCommands.getCommandsYML().getConfig().getString("editing.name");
    }

    public void changeCommandName() {
        Player editor = Bukkit.getPlayerExact(powerCommands.getCommandsYML().getConfig().getString("editing.player"));
        powerCommands.getCommandsYML().getConfig().set("editing.changing-name", true);
        editor.closeInventory();
        editor.sendMessage(StringManager.getConfigMessage("change-command-name").replace("?PREFIX?", StringManager.getPrefix()));
        powerCommands.getCommandsYML().saveConfig();
    }

    public void setCommandPerm() {
        this.perm = powerCommands.getCommandsYML().getConfig().getString("editing.perm");
    }

    public void changeCommandPerm() {
        Player editor = Bukkit.getPlayerExact(powerCommands.getCommandsYML().getConfig().getString("editing.player"));
        powerCommands.getCommandsYML().getConfig().set("editing.changing-perm", true);
        editor.closeInventory();
        editor.sendMessage(StringManager.getConfigMessage("change-command-perm").replace("?PREFIX?", StringManager.getPrefix()));
        powerCommands.getCommandsYML().saveConfig();
    }

    public void setConsoleUse(boolean con_use) {
        this.con_use = powerCommands.getCommandsYML().getConfig().getBoolean("editing.con_use");
    }

    public void changeConsoleUse() {
        if (powerCommands.getCommandsYML().getConfig().getBoolean("editing.con_use")) {
            con_use = false;
            setEditing(powerCommands.getCommandsYML().getConfig().getString("editing.name"), Bukkit.getPlayerExact(powerCommands.getCommandsYML().getConfig().getString("editing.player")));
            Bukkit.getPlayerExact(powerCommands.getCommandsYML().getConfig().getString("editing.player")).openInventory(inventoryManager.getInventory("creation"));
        } else {
            con_use = true;
            setEditing(powerCommands.getCommandsYML().getConfig().getString("editing.name"), Bukkit.getPlayerExact(powerCommands.getCommandsYML().getConfig().getString("editing.player")));
            Bukkit.getPlayerExact(powerCommands.getCommandsYML().getConfig().getString("editing.player")).openInventory(inventoryManager.getInventory("creation"));
        }
    }

    public String formatCommandVars(String s) {
        setCommandName();
        return s.replace("?CMD?", name).replace("?PERM?", powerCommands.getCommandsYML().getConfig().getString("editing.perm")).replace("?CON_USE?", String.valueOf(powerCommands.getCommandsYML().getConfig().getBoolean("editing.con_use")));
    }

    public List<String> formatCommandVars(List<String> list) {
        setCommandName();
        List<String> l = new ArrayList<>();
        for (String s : list) {
            l.add(s.replace("?CMD?", name).replace("?PERM?", powerCommands.getCommandsYML().getConfig().getString("editing.perm")).replace("?CON_USE?", String.valueOf(powerCommands.getCommandsYML().getConfig().getBoolean("editing.con_use"))));
        }
        return l;
    }

    public void setEditing(String cmd, Player p) {
        powerCommands.getCommandsYML().getConfig().set("editing.name", cmd);
        powerCommands.getCommandsYML().getConfig().set("editing.perm", perm);
        powerCommands.getCommandsYML().getConfig().set("editing.con_use", con_use);
        powerCommands.getCommandsYML().getConfig().set("editing.player", p.getName());
        powerCommands.getCommandsYML().getConfig().set("editing.changing-name", false);
        powerCommands.getCommandsYML().getConfig().set("editing.changing-perm", false);
        powerCommands.getCommandsYML().saveConfig();
    }

    @EventHandler
    public void onPlayerAsyncChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (p != Bukkit.getPlayerExact(powerCommands.getCommandsYML().getConfig().getString("editing.player")))
            return;
        String msg = event.getMessage();
        if (powerCommands.getCommandsYML().getConfig().getBoolean("editing.changing-name")) {
            if (msg.equalsIgnoreCase("cancel-cmd")) {
                Bukkit.getScheduler().runTaskLater(powerCommands, () -> {
                    p.openInventory(inventoryManager.getInventory("creation"));
                }, 3);
                powerCommands.getCommandsYML().getConfig().set("editing.changing-name", false);
                powerCommands.getCommandsYML().saveConfig();
                event.setCancelled(true);
                return;
            }
            setEditing(msg.toLowerCase(), p);
            Bukkit.getScheduler().runTaskLater(powerCommands, () -> {
                p.openInventory(inventoryManager.getInventory("creation"));
            }, 3);
            event.setCancelled(true);
            return;
        }
        if (powerCommands.getCommandsYML().getConfig().getBoolean("editing.changing-perm")) {
            if (msg.equalsIgnoreCase("cancel-cmd")) {
                Bukkit.getScheduler().runTaskLater(powerCommands, () -> {
                    p.openInventory(inventoryManager.getInventory("creation"));
                }, 3);
                powerCommands.getCommandsYML().getConfig().set("editing.changing-perm", false);
                powerCommands.getCommandsYML().saveConfig();
                event.setCancelled(true);
                return;
            }
            this.perm = msg.toLowerCase();
            setEditing(powerCommands.getCommandsYML().getConfig().getString("editing.name"), p);
            Bukkit.getScheduler().runTaskLater(powerCommands, () -> {
                p.openInventory(inventoryManager.getInventory("creation"));
            }, 3);
            event.setCancelled(true);
        }
    }

}