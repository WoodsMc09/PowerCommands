package me.woodsmc.powercommands;

import me.woodsmc.powercommands.actions.ActionsYML;
import me.woodsmc.powercommands.command.Command;
import me.woodsmc.powercommands.command.CommandManager;
import me.woodsmc.powercommands.command.CommandsYML;
import me.woodsmc.powercommands.commands.PowerCommandsCommand;
import me.woodsmc.powercommands.inventory.InventoryManager;
import me.woodsmc.powercommands.messages.StringManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class PowerCommands extends JavaPlugin {

    private CommandsYML commandsYML;
    private ActionsYML actionsYML;
    private boolean vaultEnabled;
    private boolean worleditEnabled;
    private List<String> softDependencies;
    private Economy economy;
    private static PowerCommandsAPI api;


    @Override
    public void onEnable() {
        PluginManager pM = getServer().getPluginManager();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //files
        commandsYML = new CommandsYML(this);
        actionsYML = new ActionsYML(this);
        File file = new File(this.getDataFolder(), "commandmanager/README.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write("me.woodsmc.powercommands.plugins.commandmanager.README.txt\n" +
                        "\n" +
                        "Please do not change anything in any of these files in these files!\n" +
                        "If you do change any of these files Created commands will NOT work!\n" +
                        "\n" +
                        "\n" +
                        "HOW TO FIX IF YOU CHANGED THEM\n" +
                        "Try to do CTRL+Z (Windows) CMD+Z (Mac) to undo what you did. If this doesn't work CONTACT DEV\n" +
                        "\n" +
                        "WAYS TO CONTACT:\n" +
                        "Discord: https://discord.com/invite/5rJeEN8V7H\n" +
                        "Discord DM: WoodsGeorgeJr#2995\n" +
                        "SpigotMc Conversation: https://www.spigotmc.org/members/woodsgeorgejr.1084884/");
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        reloadConfig();

        //commands
        getCommand("powercommands").setExecutor(new PowerCommandsCommand());
        getCommand("powercommands").setTabCompleter(new PowerCommandsCommand());

        for(String s : commandsYML.getConfig().getStringList("commands")){
            try {
                final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                bukkitCommandMap.setAccessible(true);
                CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

                commandMap.register(s, new Command(s));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        //listeners
        pM.registerEvents(new InventoryManager(), this);
        pM.registerEvents(new CommandManager(), this);

        //soft dependencies
        softDependencies = new ArrayList<>();
        softDependencies.add("Vault");
        for(String s : softDependencies){
            if(getServer().getPluginManager().getPlugin(s) == null){
                getLogger().log(Level.INFO, "Disabling features from " + s + "...");
            }
            else{
                getLogger().log(Level.INFO, "Enabling features from " + s + "...");
            }
        }

        api = new PowerCommandsAPI();

        //enable meesage
        getServer().getConsoleSender().sendMessage(StringManager.getPrefix() + " §dHas enabled successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public CommandsYML getCommandsYML() {
        return commandsYML;
    }

    public ActionsYML getActionsYML() {
        return actionsYML;
    }

    public Economy getEconomy() {
        return economy;
    }

    public static PowerCommandsAPI getAPI(){
        return api;
    }


}
