package me.woodsmc.powercommands.inventory;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public class InventoryManager implements Listener {
    //main instance
    private final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);
    //item manager instance
    private final ItemManager iM = new ItemManager();

    public Inventory getInventory(String inv) {
        String title = StringManager.formatColorCodes(pC.getConfig().getString(inv + "-inventory.title"));
        int size = pC.getConfig().getInt(inv + "-inventory.size");
        Inventory inventory = Bukkit.createInventory(null, size, title);
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
        for(String s : pC.getConfig().getKeys(false)){
            if(s.contains("-inventory")){
                if(event.getView().getTitle().equalsIgnoreCase(StringManager.formatColorCodes(pC.getConfig().getString(s + ".title")))){
                    event.setCancelled(true);
                }
            }
        }
    }
}
