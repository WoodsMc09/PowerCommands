package me.woodsmc.powercommands.actions.actionlib;

import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Item extends ActionManager {

    public static void executeGiveItem(Player p, ItemStack i){
        p.getInventory().addItem(i);
    }

    public static void executeGiveItem(LivingEntity l, ItemStack i){
        if(i.getType().getKey().toString().contains("helmet")){
            l.getEquipment().setHelmet(i);
            return;
        }
        if(i.getType().getKey().toString().contains("chestplate")){
            l.getEquipment().setChestplate(i);
            return;
        }
        if(i.getType().getKey().toString().contains("leggings")){
            l.getEquipment().setLeggings(i);
            return;
        }
        if(i.getType().getKey().toString().contains("boots")){
            l.getEquipment().setBoots(i);
            return;
        }
        if(i.getType().getKey().toString().equalsIgnoreCase("minecraft:totem_of_undying") || i.getType().getKey().toString().equalsIgnoreCase("minecraft:shield")){
            l.getEquipment().setItemInOffHand(i);
            return;
        }
        l.getEquipment().setItemInMainHand(i);
    }

    public static void executeClear(Player p){
        p.getInventory().clear();
    }

    public static void executeClear(LivingEntity i){
        i.getEquipment().clear();
    }

    public static void executeRemoveItem(Player p, ItemStack i){
        p.getInventory().remove(i);
    }

    public static void executeRemoveItem(Player p, Material m){
        p.getInventory().remove(m);
    }

    public static void executeDropItem(World w, ItemStack i, Location location){
        w.dropItem(location, i);
    }

}
