package me.woodsmc.powercommands.actions.actionlib.objects;

import me.woodsmc.powercommands.actions.ActionManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.*;
import org.bukkit.Sound;
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
    //create object
    //TODO clean up these methods a bit


    public static PotionEffectType executeCreatePotionEffectType(String type){
        //get potion effect from name
        PotionEffectType potionEffectType = PotionEffectType.getByName(type.toUpperCase());
        //check if eggect is null
        if(potionEffectType == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreatePotionEffectType." + type + " not found!");

        //return effect
        return potionEffectType;
    }

    public static Location executeCreateLocation(double x, double y, double z, String w){
        //get world from string
        World world = Bukkit.getWorld(w.toLowerCase());

        //check if world is null
        if(world == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateLocation.world." + w + " not found!");

        //create location
        Location location = new Location(world, x, y, z);
        return location;
    }

    public static Material executeCreateMaterial(String mat){
        //get material from string
        Material material = Material.getMaterial(mat.toUpperCase());

        //check if material is null
        if(material == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateMaterial." + mat + " not found!");

        //return material
        return material;
    }

    public static ItemStack executeCreateItem(Material material, int i){
        //create item from material
        ItemStack item = new ItemStack(material, i);

        //return the item
        return item;
    }

    public static ItemStack executeCreateItem(Material material, int i, String name){
        //create item from material
        ItemStack item = new ItemStack(material, i);
        //get item meta
        ItemMeta meta = item.getItemMeta();

        //set item name
        meta.setDisplayName(StringManager.formatColorCodes(name));
        //set item meta
        item.setItemMeta(meta);
        //return the item
        return item;
    }

    public static ItemStack executeCreateItem(Material material, int i, String name, List<String> lore){
        //create item from material
        ItemStack item = new ItemStack(material, i);
        //get item meta
        ItemMeta meta = item.getItemMeta();

        //set item name
        meta.setDisplayName(StringManager.formatColorCodes(name));
        //create lore list
        List<String> l = new ArrayList<>();
        //loop through lore
        for(String s : lore){
            //add string to list
            l.add(StringManager.formatColorCodes(s));
        }
        //set lore
        meta.setLore(l);
        //set item meta
        item.setItemMeta(meta);
        //return the item
        return item;
    }

    public static ItemStack executeCreateSkull(OfflinePlayer p, int i){
        //create item as a player head
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, i);
        //get item skull meta
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        //set owning player
        meta.setOwningPlayer(p);
        //set item meta
        item.setItemMeta(meta);
        //return the item
        return item;
    }

    public static ItemStack executeCreateSkull(OfflinePlayer p, int i, String name){
        //create item as a player head
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, i);
        //get item skull meta
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        //set item name
        meta.setDisplayName(StringManager.formatColorCodes(name));
        //set owning player
        meta.setOwningPlayer(p);
        //set item meta
        item.setItemMeta(meta);
        //return the item
        return item;
    }

    public static ItemStack executeCreateSkull(OfflinePlayer p, int i, String name, List<String> lore){
        //create item as a player head
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, i);
        //get item skull meta
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        //set item name
        meta.setDisplayName(StringManager.formatColorCodes(name));
        //set owning player
        meta.setOwningPlayer(p);

        //create lore list
        List<String> l = new ArrayList<>();
        //loop through lore
        for(String s : lore){
            //add string to lore list
            l.add(StringManager.formatColorCodes(s));
        }
        //set lore
        meta.setLore(l);
        //set item meta
        item.setItemMeta(meta);
        //return the item
        return item;
    }

    public static UUID executeCreateUUID(String id){
        //create a uuid
        UUID uuid = UUID.fromString(id);
        return uuid;
    }

    public static Player executeCreatePlayer(String name){
        //create a player from name
        Player p = Bukkit.getPlayerExact(name);
        return p;
    }

    public static Player executeCreatePlayer(UUID uuid){
        //create a player from uuid
        Player p = Bukkit.getPlayer(uuid);
        return p;
    }

    public static OfflinePlayer executeCreateOfflinePlayer(String name){
        //create offline player from name
        OfflinePlayer p = Bukkit.getOfflinePlayer(name);
        return p;
    }

    public static OfflinePlayer executeCreateOfflinePlayer(UUID uuid){
        //create offline player from uuid
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
        return p;
    }

    public static Sound executeCreateSound(String name){
        //create sound from name
        Sound sound = Sound.valueOf(name);
        return sound;
    }

    public static World executeCreateWorld(String name){
        //create world from name
        World world = Bukkit.getWorld(name.toLowerCase());

        //check if world is null
        if(world == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateWorld." + name + " not found!");
        //return the world
        return world;
    }

    public static EntityType executeCreateEntityType(String name){
        //create entity type from name
        EntityType entity = EntityType.valueOf(name.toUpperCase());

        //check if entity type is null
        if(entity == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeCreateEntityType." + name + " not found!");
        //return the entity type
        return entity;
    }
}
