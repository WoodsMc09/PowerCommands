package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class Teleport extends ActionManager {
    //teleport entity

    public static void executeTeleport(Entity e, Location location){
        e.teleport(location);
    }

    public static void executeTeleport(List<Entity> e, Location location){
        //loop through entities
        for(Entity entity : e) {
            //teleport them
            entity.teleport(location);
        }
    }


    public static List<ItemStack> getAttributes(){
        List<ItemStack> list = new ArrayList<>();
        //Entity
        ItemStack entity = new ItemStack(Material.SPAWNER);
        ItemMeta entityMeta = entity.getItemMeta();
        entityMeta.setDisplayName("§d§lCHOOSE ENTITY");
        List<String> entityLore = new ArrayList<>();
        entityLore.add("§7Left-Click to choose the entity");
        entityMeta.setLore(entityLore);
        entity.setItemMeta(entityMeta);

        list.add(entity);

        //Location
        ItemStack location = new ItemStack(Material.MAP);
        ItemMeta locationMeta = location.getItemMeta();
        locationMeta.setDisplayName("§d§lCHOOSE LOCATION");
        List<String> locationLore = new ArrayList<>();
        locationLore.add("§7Left-Click to choose the location");
        locationMeta.setLore(locationLore);

        list.add(location);

        return list;
    }
}











