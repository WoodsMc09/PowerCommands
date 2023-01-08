package me.woodsmc.powercommands.inventory;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.command.CommandManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemManager {
    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);
    //command manager instance
    private static final CommandManager cM = new CommandManager();

    public Map<Integer, ItemStack> getInventoryContents(String inv) {
        //create map of the item and slot index
        Map<Integer, ItemStack> contents = new HashMap<>();

        //get configuration section for the items
        ConfigurationSection itemsSection = pC.getConfig().getConfigurationSection(inv + "-inventory.items");
        if (itemsSection == null) {
            return contents;
        }

        //loop through all keys in the item
        for (String item : itemsSection.getKeys(false)) {
            if (item.equalsIgnoreCase("title") || item.equalsIgnoreCase("size")) {
                continue;
            }

            //get item material
            Material mat = Material.getMaterial(pC.getConfig().getString(inv + "-inventory.items." + item + ".type").toUpperCase());
            //get item title
            String title = StringManager.formatColorCodes(pC.getConfig().getString(inv + "-inventory.items." + item + ".title"));
            //get item slot index
            int slot = pC.getConfig().getInt(inv + "-inventory.items." + item + ".slot");
            //get item lore
            List<String> lore = pC.getConfig().getStringList(inv + "-inventory.items." + item + ".lore");

            //create item
            ItemStack itemStack = new ItemStack(mat);
            //get item meta
            ItemMeta meta = itemStack.getItemMeta();
            //set item name
            meta.setDisplayName(title);
            //set item lore
            meta.setLore(cM.formatCommandVars(StringManager.formatListColorCodes(lore)));
            //set item meta
            itemStack.setItemMeta(meta);
            //null check for no slot
            if(pC.getConfig().get(inv + "-inventory.items." + item + ".slot") == null)
                return contents;
            else
                //return contents and slot indexes
                contents.put(slot, itemStack);
        }

        return contents;
    }

    //get action items
    public ItemStack getActionitem(String action){
        //create item
        ItemStack item = new ItemStack(Material.getMaterial(pC.getConfig().getString("action-select-inventory.items.action-type.type").toUpperCase()));
        //get item meta
        ItemMeta meta = item.getItemMeta();
        //set display name
        meta.setDisplayName(StringManager.formatColorCodes(pC.getConfig().getString("action-select-inventory.items.action-type.title").replace("?ACTION?", action)));
        //create lore
        List<String> lore = StringManager.formatListColorCodes(pC.getConfig().getStringList("action-select-inventory.items.action-type.lore"));

        //set item lore
        meta.setLore(lore);
        //set item meta
        item.setItemMeta(meta);
        //return item
        return item;
    }

    public String getConfigItemName(String inv, String item){
        //get item name from configuration, formatted.
        return StringManager.formatColorCodes(pC.getConfig().getString(inv + "-inventory.items." + item + ".title"));
    }


}
