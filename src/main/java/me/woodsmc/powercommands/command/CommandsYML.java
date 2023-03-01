package me.woodsmc.powercommands.command;

import me.woodsmc.powercommands.PowerCommands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class CommandsYML {

    private final PowerCommands plugin;
    private FileConfiguration configuration = null;
    private File configFile = null;

    public CommandsYML(PowerCommands plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "commandmanager/commands.yml");
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("commandmanager/commands.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.configuration.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.configuration == null) {
            reloadConfig();
        }
        return this.configuration;
    }

    public void saveConfig() {
        if (this.configuration == null || this.configFile == null) {
        }
        try {
            getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "§cCould not save config to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(plugin.getDataFolder(), "commandmanager/commands.yml");
        }
        if (!this.configFile.exists()) {
            plugin.saveResource("commandmanager/commands.yml", false);
        }
    }
}