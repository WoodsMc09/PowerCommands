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
import java.util.logging.Level;

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
            List<Class<?>> classes = aM.getActionsClasses();
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
        if(event.getClickedInventory() == null)
            return;
        if(event.getCurrentItem() == null)
            return;
        if(event.getCurrentItem().getItemMeta() == null)
            return;
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null)
            return;
        ItemStack item = event.getCurrentItem();
        Player p = (Player) event.getWhoClicked();
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("creation", "actions-editor"))){
            p.openInventory(getInventory("actions"));
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("actions", "back"))){
            p.openInventory(getInventory("creation"));
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("creation", "name-editor"))){
            cM.changeCommandName();
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("creation", "permission-editor"))){
            cM.changeCommandPerm();
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("creation", "console-use"))){
            cM.changeConsoleUse();
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("actions", "create-action"))){
            p.openInventory(getInventory("action-create"));
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("creation", "arguments-editor"))){
            p.openInventory(getInventory("arguments"));
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(iM.getConfigItemName("action-create", "select-action"))){
            p.openInventory(getInventory("action-select"));
            return;
        }

        for(String s : pC.getConfig().getKeys(false)){
            if(s.contains("-inventory")){
                if(event.getView().getTitle().equalsIgnoreCase(StringManager.formatColorCodes(pC.getConfig().getString(s + ".title")))){
                    event.setCancelled(true);
                }
            }
        }
    }



}
