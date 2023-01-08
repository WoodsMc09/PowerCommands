package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Item extends ActionManager {
    //item


    public static void executeGiveItem(Player p, ItemStack i){
        //add item
        p.getInventory().addItem(i);
    }

    //TODO clean up this methods if statments
    public static void executeGiveItem(LivingEntity l, ItemStack i){
        //check if it is an armor piece
        if(i.getType().getKey().toString().contains("helmet")){
            //set helmet
            l.getEquipment().setHelmet(i);
            return;
        }
        if(i.getType().getKey().toString().contains("chestplate")){
            //set chestplate
            l.getEquipment().setChestplate(i);
            return;
        }
        if(i.getType().getKey().toString().contains("leggings")){
            //set leggings
            l.getEquipment().setLeggings(i);
            return;
        }
        if(i.getType().getKey().toString().contains("boots")){
            //set boots
            l.getEquipment().setBoots(i);
            return;
        }
        //check if it is specified for offhand
        if(i.getType().getKey().toString().equalsIgnoreCase("minecraft:totem_of_undying") || i.getType().getKey().toString().equalsIgnoreCase("minecraft:shield")){
            //set offhand
            l.getEquipment().setItemInOffHand(i);
            return;
        }
        //set mainhand
        l.getEquipment().setItemInMainHand(i);
    }

    public static void executeClear(Player p){
        //clear inventory
        p.getInventory().clear();
    }

    public static void executeClear(LivingEntity i){
        //clear mob
        i.getEquipment().clear();
    }

    public static void executeRemoveItem(Player p, ItemStack i){
        //remove item
        p.getInventory().remove(i);
    }

    public static void executeRemoveItem(Player p, Material m){
        //remove material
        p.getInventory().remove(m);
    }

    public static void executeDropItem(World w, ItemStack i, Location location){
        //drop item
        w.dropItem(location, i);
    }

}
