package me.woodsmc.powercommands.actions.actionlib.objects;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public class GetObject extends ActionManager {
    //get object
    //TODO clean up these methods a bit

    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static double executeGetLivingEntityHealth(LivingEntity e){
        //get entity health
        return e.getHealth();
    }

    public static int executeGetPlayerFoodLevel(Player p){
        //get player food level
        return p.getFoodLevel();
    }

    public static int executeGetPlayerStatistic(Player p, String statistic){
        //statistic instance
        Statistic stat = Statistic.valueOf(statistic.toUpperCase());
        //check if stat is null
        if(stat == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeGetPlayerStatistic." + statistic + " not found!");

        //return player statistic
        return p.getStatistic(stat);
    }

    public static Location executeGetEntityLocation(Entity e){
        //get entity location
        return e.getLocation();
    }

    public static float executeGetPlayerWalkingSpeed(Player p){
        //get player walking speed
        return p.getWalkSpeed();
    }

    public static float executeGetPlayerFlyingSpeed(Player p){
        //get player flying speed
        return p.getFlySpeed();
    }

    public static String executeGetPlayerGroup(Player p){
        //get player group
        return pC.getActionsYML().getConfig().getString(String.valueOf(p.getUniqueId()));
    }

    public static double executeGetPlayerBalance(OfflinePlayer p){
        //get player balance
        return pC.getEconomy().getBalance(p);
    }

    public static List<Entity> executeGetNearbyEntities(Location location, int x, int y, int z){
        //get nearby entities from a location
        return (List<Entity>) location.getWorld().getNearbyEntities(location, x, y, z);
    }
}
