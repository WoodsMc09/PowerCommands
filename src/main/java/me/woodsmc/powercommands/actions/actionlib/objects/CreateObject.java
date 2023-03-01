package me.woodsmc.powercommands.actions.actionlib.objects;

import me.woodsmc.powercommands.actions.ActionManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class CreateObject extends ActionManager {


    public static PotionEffectType executeCreatePotionEffectType(String type) {
        PotionEffectType potionEffectType = PotionEffectType.getByName(type.toUpperCase());
        if (potionEffectType == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreatePotionEffectType." + type + " not found!");
        return potionEffectType;
    }

    public static Location executeCreateLocation(double x, double y, double z, String w) {
        World world = Bukkit.getWorld(w.toLowerCase());
        if (world == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateLocation.world." + w + " not found!");
        Location location = new Location(world, x, y, z);
        return location;
    }

    public static Material executeCreateMaterial(String mat) {
        Material material = Material.getMaterial(mat.toUpperCase());
        if (material == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateMaterial." + mat + " not found!");
        return material;
    }

    public static ItemStack executeCreateItem(Material material, int i) {
        ItemStack item = new ItemStack(material, i);
        return item;
    }

    public static ItemStack executeCreateItem(Material material, int i, String name) {
        ItemStack item = new ItemStack(material, i);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringManager.formatColorCodes(name));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack executeCreateItem(Material material, int i, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, i);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringManager.formatColorCodes(name));
        List<String> l = new ArrayList<>();
        for (String s : lore) {
            l.add(StringManager.formatColorCodes(s));
        }
        meta.setLore(l);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack executeCreateSkull(OfflinePlayer p, int i) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, i);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(p);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack executeCreateSkull(OfflinePlayer p, int i, String name) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, i);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(StringManager.formatColorCodes(name));
        meta.setOwningPlayer(p);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack executeCreateSkull(OfflinePlayer p, int i, String name, List<String> lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, i);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(StringManager.formatColorCodes(name));
        meta.setOwningPlayer(p);
        List<String> l = new ArrayList<>();
        for (String s : lore) {
            l.add(StringManager.formatColorCodes(s));
        }
        meta.setLore(l);
        item.setItemMeta(meta);
        return item;
    }

    public static UUID executeCreateUUID(String id) {
        UUID uuid = UUID.fromString(id);
        return uuid;
    }

    public static Player executeCreatePlayer(String name) {
        Player p = Bukkit.getPlayerExact(name);
        return p;
    }

    public static Player executeCreatePlayer(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        return p;
    }

    public static OfflinePlayer executeCreateOfflinePlayer(String name) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(name);
        return p;
    }

    public static OfflinePlayer executeCreateOfflinePlayer(UUID uuid) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
        return p;
    }

    public static Sound executeCreateSound(String name) {
        Sound sound = Sound.valueOf(name);
        return sound;
    }

    public static World executeCreateWorld(String name) {
        World world = Bukkit.getWorld(name.toLowerCase());
        if (world == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateWorld." + name + " not found!");
        return world;
    }

    public static EntityType executeCreateEntityType(String name) {
        EntityType entity = EntityType.valueOf(name.toUpperCase());
        if (entity == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateEntityType." + name + " not found!");
        return entity;
    }
}
