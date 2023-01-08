package me.woodsmc.powercommands.commands;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.command.CommandManager;
import me.woodsmc.powercommands.inventory.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PowerCommandsCommand implements CommandExecutor, TabCompleter {
    //main instance
    private final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);
    //inventory manager instance
    private final InventoryManager iM = new InventoryManager();
    //command manager instance
    private static final CommandManager cM = new CommandManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            //is player
            Player p = (Player) sender;

            //check argument array length
            if(args.length != 2){
                //not 2 arguments
                //send usage
                p.sendMessage("§cInsufficient usage!");
                p.sendMessage("§5§l/pc create <name> <description> §d- Create a command!");
                p.sendMessage("§5§l/pc delete <name> §d- Delete a command!");
                p.sendMessage("§5§l/pc edit <name> §d- Edit a command!");

                //return
                return true;
            }
            //is 2 arguments
            //check argument
            if(args[0].equalsIgnoreCase("create")){
                //creating
                //set editing, open creation inventory
                cM.setEditing(args[1], p);
                p.openInventory(iM.getInventory("creation"));
            }
        }else{
            //is console
            sender.sendMessage("§4§lSorry! §dConsole cannot use this command!");
        }
        return true;
    }

    //create arg[0] list (argument 1)
    List<String> arg0 = new ArrayList<>();
    List<String> resultA = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        //check argument length
        if(args.length == 1){
            //length is 1
            //add to arg[0] list
            arg0.add("create");
            arg0.add("delete");
            arg0.add("edit");
            //loop through arg[0] list
            for(String a : arg0){
                //if arguments start with the same letter
                if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                    //add argument
                    resultA.add(a);
                }
                //return argument
                return resultA;
            }
        }

        return null;
    }
}
