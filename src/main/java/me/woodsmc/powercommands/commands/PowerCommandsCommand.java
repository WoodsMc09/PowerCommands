package me.woodsmc.powercommands.commands;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.inventory.InventoryManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PowerCommandsCommand implements CommandExecutor, TabCompleter {
    //main instance
    private final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);
    //inventory manager instance
    private final InventoryManager iM = new InventoryManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            //is player
            Player p = (Player) sender;
            if(args.length != 2){
                p.sendMessage("§cInsufficient usage!");
                p.sendMessage("§5§l/pc create <name> §d- Create a command!");
                p.sendMessage("§5§l/pc delete <name> §d- Delete a command!");
                p.sendMessage("§5§l/pc edit <name> §d- Edit a command!");
                return true;
            }
            if(args[0].equalsIgnoreCase("create")){
                StringManager.cmd = args[1];
                p.openInventory(iM.getInventory("creation"));
            }
        }else{
            //is console
            sender.sendMessage("§4§lSorry! §dConsole cannot use this command!");
        }
        return true;
    }

    List<String> arg0 = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1){
            arg0.add("create");
            arg0.add("delete");
            arg0.add("edit");
        }
        List<String> resultA = new ArrayList<>();
        if(args.length == 1){
            for(String a : arg0){
                if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                    resultA.add(a);
                }
                return resultA;
            }
        }

        return null;
    }
}
