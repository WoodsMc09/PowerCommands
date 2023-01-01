package me.woodsmc.powercommands.actions.actionlib.objects;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class IfObject extends ActionManager {
    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static boolean executeIfLivingEntityHealth(LivingEntity l, String operation, double d){
        if(operation.equalsIgnoreCase("==")){
            return l.getHealth() == d;
        }
        if(operation.equalsIgnoreCase(">")){
            return l.getHealth() > d;
        }
        if(operation.equalsIgnoreCase("<")){
            return l.getHealth() < d;
        }
        if(operation.equalsIgnoreCase(">=")){
            return l.getHealth() >= d;
        }
        if(operation.equalsIgnoreCase("<=")){
            return l.getHealth() <= d;
        }
        return false;
    }

    public static boolean executeIfPlayerFoodLevel(Player p, String operation, int i){
        if(operation.equalsIgnoreCase("==")){
            return p.getFoodLevel() == i;
        }
        if(operation.equalsIgnoreCase(">")){
            return p.getFoodLevel() > i;
        }
        if(operation.equalsIgnoreCase("<")){
            return p.getFoodLevel() < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            return p.getFoodLevel() >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            return p.getFoodLevel() <= i;
        }
        return false;
    }

    public static boolean executeIfPlayerStatistic(int stat, String operation, int i){
        if(operation.equalsIgnoreCase("==")){
            return stat == i;
        }
        if(operation.equalsIgnoreCase(">")){
            return stat > i;
        }
        if(operation.equalsIgnoreCase("<")){
            return stat < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            return stat >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            return stat <= i;
        }
        return false;
    }

    public static boolean executeIfLocationIsEqualTo(Location loc1, Location loc2){
        return loc1 == loc2;
    }

    public static boolean executeIfPlayerWalkingSpeed(Player p, String operation, int i){
        if(operation.equalsIgnoreCase("==")){
            return p.getWalkSpeed() == i;
        }
        if(operation.equalsIgnoreCase(">")){
            return p.getWalkSpeed() > i;
        }
        if(operation.equalsIgnoreCase("<")){
            return p.getWalkSpeed() < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            return p.getWalkSpeed() >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            return p.getWalkSpeed() <= i;
        }
        return false;
    }

    public static boolean executeIfPlayerFlyingSpeed(Player p, String operation, int i){
        if(operation.equalsIgnoreCase("==")){
            return p.getFlySpeed() == i;
        }
        if(operation.equalsIgnoreCase(">")){
            return p.getFlySpeed() > i;
        }
        if(operation.equalsIgnoreCase("<")){
            return p.getFlySpeed() < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            return p.getFlySpeed() >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            return p.getFlySpeed() <= i;
        }
        return false;
    }

    public static boolean executeIfPlayerGroupIsEqualTo(Player p, String s){
        return pC.getActionsYML().getConfig().getString(String.valueOf(p.getUniqueId())).equalsIgnoreCase(s);
    }

    public static boolean executeIfPlayerBalance(OfflinePlayer p, String operation, int i){
        if(operation.equalsIgnoreCase("==")){
            return pC.getEconomy().getBalance(p) == i;
        }
        if(operation.equalsIgnoreCase(">")){
            return pC.getEconomy().getBalance(p) > i;
        }
        if(operation.equalsIgnoreCase("<")){
            return pC.getEconomy().getBalance(p) < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            return pC.getEconomy().getBalance(p) >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            return pC.getEconomy().getBalance(p) <= i;
        }
        return false;
    }

    public static boolean executeIfNumber(int number1, String operation, int number2){
        if(operation.equalsIgnoreCase("==")){
            return number1 == number2;
        }
        if(operation.equalsIgnoreCase(">")){
            return number1 > number2;
        }
        if(operation.equalsIgnoreCase("<")){
            return number1 < number2;
        }
        if(operation.equalsIgnoreCase(">=")){
            return number1 >= number2;
        }
        if(operation.equalsIgnoreCase("<=")){
            return number1 <= number2;
        }
        return false;
    }
}
