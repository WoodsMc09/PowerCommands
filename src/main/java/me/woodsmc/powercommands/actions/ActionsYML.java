package me.woodsmc.powercommands.actions;

import me.woodsmc.powercommands.PowerCommands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class ActionsYML {

    private final PowerCommands plugin;
    private FileConfiguration configuration = null;
    private File configFile = null;


    public ActionsYML(PowerCommands plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "commandmanager/actions.yml");
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("commandmanager/actions.yml");
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
            this.configFile = new File(plugin.getDataFolder(), "commandmanager/actions.yml");
        }
        if (!this.configFile.exists()) {
            plugin.saveResource("commandmanager/actions.yml", false);
        }
    }
}
