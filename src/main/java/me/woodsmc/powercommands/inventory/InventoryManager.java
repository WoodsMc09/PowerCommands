package me.woodsmc.powercommands.inventory;


import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.actions.ActionManager;
import me.woodsmc.powercommands.command.CommandManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class InventoryManager implements Listener {
    //main instance
    private final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);
    //item manager instance
    private final ItemManager iM = new ItemManager();
    //command manager instance
    private static final CommandManager cM = new CommandManager();
    //action manager instance
    private final ActionManager aM = new ActionManager();

    public Inventory getInventory(String inv) {
        String title = StringManager.formatColorCodes(cM.formatCommandVars(pC.getConfig().getString(inv + "-inventory.title")));
        int size = pC.getConfig().getInt(inv + "-inventory.size");
        Inventory inventory = Bukkit.createInventory(null, size, title);

        if(inv.equalsIgnoreCase("action-select")){
            List<Class<?>> classes = aM.getActionLibrary();

            for(int i = 0; i < classes.size(); i++){
                String name = classes.get(i).getName().replace("me.woodsmc.powercommands.actions.actionlib.", "").replace("objects.", "");
                inventory.addItem(iM.getActionitem(name));
            }

            return inventory;
        }

        for (Map.Entry<Integer, ItemStack> entry : iM.getInventoryContents(inv).entrySet()) {
            int slot = entry.getKey();
            ItemStack item = entry.getValue();
            inventory.setItem(slot, item);
        }

        return inventory;
    }



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        //check if clicked inv is null
        if(event.getClickedInventory() == null)
            return;
        //check if clicked item is null
        if(event.getCurrentItem() == null)
            return;
        //check if item meta is null
        if(event.getCurrentItem().getItemMeta() == null)
            return;
        //check if item name is null
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null)
            return;

        //get clicked item
        ItemStack item = event.getCurrentItem();
        //get player (who clicked)
        Player p = (Player) event.getWhoClicked();

        Map<String, Runnable> actionsByDisplayName = new HashMap<>();
        String[][] displayNamesAndActions = {
                {"creation", "actions-editor", "actions"},
                {"actions", "back", "creation"},
                {"creation", "name-editor", null},
                {"creation", "permission-editor", null},
                {"creation", "console-use", null},
                {"actions", "create-action", "action-create"},
                {"creation", "arguments-editor", "arguments"},
                {"action-create", "select-action", "action-select"},
                {"arguments", "create-argument", "argument-create"}
        };

        for (String[] displayNameAndAction : displayNamesAndActions) {
            String displayName = iM.getConfigItemName(displayNameAndAction[0], displayNameAndAction[1]);
            Runnable action;
            if (displayNameAndAction[1].equals("name-editor")) {
                action = () -> cM.changeCommandName();
            } else if (displayNameAndAction[1].equals("permission-editor")) {
                action = () -> cM.changeCommandPerm();
            } else if (displayNameAndAction[1].equals("console-use")) {
                action = () -> cM.changeConsoleUse();
            } else {
                action = () -> p.openInventory(getInventory(displayNameAndAction[2]));
            }
            actionsByDisplayName.put(displayName, action);
        }

        Runnable action = actionsByDisplayName.get(item.getItemMeta().getDisplayName());
        if (action != null) {
            action.run();
        }

        //loop through config keys
        for(String s : pC.getConfig().getKeys(false)){
            //check if it is an inventory
            if(s.contains("-inventory")){
                //check if title is an inventory
                if(event.getView().getTitle().equalsIgnoreCase(StringManager.formatColorCodes(pC.getConfig().getString(s + ".title")))){
                    //cancel event
                    event.setCancelled(true);
                }
            }
        }
    }



}
