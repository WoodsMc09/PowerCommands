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

import java.util.List;
import java.util.Map;

public class InventoryManager implements Listener {
    //command manager instance
    private static final CommandManager COMMAND_MANAGER = new CommandManager();
    //main instance
    private final PowerCommands powerCommands = PowerCommands.getPlugin(PowerCommands.class);
    //item manager instance
    private final ItemManager itemManager = new ItemManager();
    //action manager instance
    private final ActionManager actionManager = new ActionManager();

    public Inventory getInventory(String inv) {
        String title = StringManager.formatColorCodes(COMMAND_MANAGER.formatCommandVars(powerCommands.getConfig().getString(inv + "-inventory.title")));
        int size = powerCommands.getConfig().getInt(inv + "-inventory.size");
        Inventory inventory = Bukkit.createInventory(null, size, title);
        if (inv.equalsIgnoreCase("action-select")) {
            List<Class<?>> classes = actionManager.getActionsClasses();
            for (Class<?> aClass : classes) {
                String name = aClass.getName().replace("me.woodsmc.powercommands.actions.actionlib.", "").replace("objects.", "");
                inventory.addItem(itemManager.getActionitem(name));
            }

            return inventory;
        }
        for (Map.Entry<Integer, ItemStack> entry : itemManager.getInventoryContents(inv).entrySet()) {
            int slot = entry.getKey();
            ItemStack item = entry.getValue();
            inventory.setItem(slot, item);
        }

        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        ItemStack item = event.getCurrentItem();
        Player p = (Player) event.getWhoClicked();

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("creation", "actions-editor"))) {
            p.openInventory(getInventory("actions"));
            return;
        }

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("actions", "back"))) {
            p.openInventory(getInventory("creation"));
            return;
        }

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("creation", "name-editor"))) {
            COMMAND_MANAGER.changeCommandName();
            return;
        }

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("creation", "permission-editor"))) {
            COMMAND_MANAGER.changeCommandPerm();
            return;
        }

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("creation", "console-use"))) {
            COMMAND_MANAGER.changeConsoleUse();
            return;
        }

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("actions", "create-action"))) {
            p.openInventory(getInventory("action-create"));
            return;
        }

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("creation", "arguments-editor"))) {
            p.openInventory(getInventory("arguments"));
            return;
        }

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(itemManager.getConfigItemName("action-create", "select-action"))) {
            p.openInventory(getInventory("action-select"));
            return;
        }

        for (String s : powerCommands.getConfig().getKeys(false)) {
            if (s.contains("-inventory")) {
                if (event.getView().getTitle().equalsIgnoreCase(StringManager.formatColorCodes(powerCommands.getConfig().getString(s + ".title")))) {
                    event.setCancelled(true);
                }
            }
        }
    }
}