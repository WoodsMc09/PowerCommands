package me.woodsmc.powercommands.actions.actionlib.objects;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actions.ActionManager;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class IfObject extends ActionManager {
    //if object
    //TODO clean up these methods a bit

    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    public static boolean executeIfLivingEntityHealth(LivingEntity l, String operation, double d){
        //if entity health
        if(operation.equalsIgnoreCase("==")){
            //equal to
            return l.getHealth() == d;
        }
        if(operation.equalsIgnoreCase(">")){
            //greater than
            return l.getHealth() > d;
        }
        if(operation.equalsIgnoreCase("<")){
            //less than
            return l.getHealth() < d;
        }
        if(operation.equalsIgnoreCase(">=")){
            //greater than or equal to
            return l.getHealth() >= d;
        }
        if(operation.equalsIgnoreCase("<=")){
            //less than or equal to
            return l.getHealth() <= d;
        }
        return false;
    }

    public static boolean executeIfPlayerFoodLevel(Player p, String operation, int i){
        //if player food level
        if(operation.equalsIgnoreCase("==")){
            //equal to
            return p.getFoodLevel() == i;
        }
        if(operation.equalsIgnoreCase(">")){
            //greater than
            return p.getFoodLevel() > i;
        }
        if(operation.equalsIgnoreCase("<")){
            //less than
            return p.getFoodLevel() < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            //greater than or equal to
            return p.getFoodLevel() >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            //less than or equal to
            return p.getFoodLevel() <= i;
        }
        return false;
    }

    public static boolean executeIfPlayerStatistic(int stat, String operation, int i){
        //if player statistic
        if(operation.equalsIgnoreCase("==")){
            //equal to
            return stat == i;
        }
        if(operation.equalsIgnoreCase(">")){
            //greater than
            return stat > i;
        }
        if(operation.equalsIgnoreCase("<")){
            //less than
            return stat < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            //greater than or equal to
            return stat >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            //less than or equal to
            return stat <= i;
        }
        return false;
    }

    public static boolean executeIfLocationIsEqualTo(Location loc1, Location loc2){
        //if location is equal to
        return loc1 == loc2;
    }

    public static boolean executeIfPlayerWalkingSpeed(Player p, String operation, int i){
        //if player walking speed
        if(operation.equalsIgnoreCase("==")){
            //equal to
            return p.getWalkSpeed() == i;
        }
        if(operation.equalsIgnoreCase(">")){
            //greater than
            return p.getWalkSpeed() > i;
        }
        if(operation.equalsIgnoreCase("<")){
            //less than
            return p.getWalkSpeed() < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            //greater than or equal to
            return p.getWalkSpeed() >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            //less than or equal to
            return p.getWalkSpeed() <= i;
        }
        return false;
    }

    public static boolean executeIfPlayerFlyingSpeed(Player p, String operation, int i){
        //if player fly speed
        if(operation.equalsIgnoreCase("==")){
            //equal to
            return p.getFlySpeed() == i;
        }
        if(operation.equalsIgnoreCase(">")){
            //greater than
            return p.getFlySpeed() > i;
        }
        if(operation.equalsIgnoreCase("<")){
            //less than
            return p.getFlySpeed() < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            //greater than or equal to
            return p.getFlySpeed() >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            //less than or equal to
            return p.getFlySpeed() <= i;
        }
        return false;
    }

    public static boolean executeIfPlayerGroupIsEqualTo(Player p, String s){
        //if player group is equal to
        return pC.getActionsYML().getConfig().getString(String.valueOf(p.getUniqueId())).equalsIgnoreCase(s);
    }

    public static boolean executeIfPlayerBalance(OfflinePlayer p, String operation, int i){
        //if player balance
        if(operation.equalsIgnoreCase("==")){
            //equal to
            return pC.getEconomy().getBalance(p) == i;
        }
        if(operation.equalsIgnoreCase(">")){
            //greater than
            return pC.getEconomy().getBalance(p) > i;
        }
        if(operation.equalsIgnoreCase("<")){
            //less than
            return pC.getEconomy().getBalance(p) < i;
        }
        if(operation.equalsIgnoreCase(">=")){
            //greater than or equal to
            return pC.getEconomy().getBalance(p) >= i;
        }
        if(operation.equalsIgnoreCase("<=")){
            //less than or equal to
            return pC.getEconomy().getBalance(p) <= i;
        }
        return false;
    }

    public static boolean executeIfNumber(int number1, String operation, int number2){
        //if number
        if(operation.equalsIgnoreCase("==")){
            //equal to
            return number1 == number2;
        }
        if(operation.equalsIgnoreCase(">")){
            //greater than
            return number1 > number2;
        }
        if(operation.equalsIgnoreCase("<")){
            //less than
            return number1 < number2;
        }
        if(operation.equalsIgnoreCase(">=")){
            //greater than or equal to
            return number1 >= number2;
        }
        if(operation.equalsIgnoreCase("<=")){
            //less than or equal to
            return number1 <= number2;
        }
        return false;
    }
}
