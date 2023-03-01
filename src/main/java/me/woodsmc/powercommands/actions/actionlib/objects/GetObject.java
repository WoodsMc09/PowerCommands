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
    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static double executeGetLivingEntityHealth(LivingEntity e) {
        return e.getHealth();
    }

    public static int executeGetPlayerFoodLevel(Player p) {
        return p.getFoodLevel();
    }

    public static int executeGetPlayerStatistic(Player p, String statistic) {
        Statistic stat = Statistic.valueOf(statistic.toUpperCase());
        if (stat == null)
            Bukkit.getLogger().log(Level.SEVERE, "[PowerCommands] executeGetPlayerStatistic." + statistic + " not found!");
        return p.getStatistic(stat);
    }

    public static Location executeGetEntityLocation(Entity e) {
        return e.getLocation();
    }

    public static float executeGetPlayerWalkingSpeed(Player p) {
        return p.getWalkSpeed();
    }

    public static float executeGetPlayerFlyingSpeed(Player p) {
        return p.getFlySpeed();
    }

    public static String executeGetPlayerGroup(Player p) {
        return pC.getActionsYML().getConfig().getString(String.valueOf(p.getUniqueId()));
    }

    public static double executeGetPlayerBalance(OfflinePlayer p) {
        return pC.getEconomy().getBalance(p);
    }

    public static List<Entity> executeGetNearbyEntities(Location location, int x, int y, int z) {
        return (List<Entity>) location.getWorld().getNearbyEntities(location, x, y, z);
    }
}
