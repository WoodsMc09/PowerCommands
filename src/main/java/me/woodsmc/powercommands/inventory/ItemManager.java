package me.woodsmc.powercommands.inventory;

import me.woodsmc.powercommands.PowerCommands;
import me.woodsmc.powercommands.command.CommandManager;
import me.woodsmc.powercommands.messages.StringManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    //main instance
    private static final PowerCommands pC = PowerCommands.getPlugin(PowerCommands.class);

    private static final CommandManager cM = new CommandManager();

    public Map<Integer, ItemStack> getInventoryContents(String inv) {
        Map<Integer, ItemStack> contents = new HashMap<>();

        ConfigurationSection itemsSection = pC.getConfig().getConfigurationSection(inv + "-inventory.items");
        if (itemsSection == null) {
            return contents;
        }

        for (String item : itemsSection.getKeys(false)) {
            if (item.equalsIgnoreCase("title") || item.equalsIgnoreCase("size")) {
                continue;
            }

            Material mat = Material.getMaterial(pC.getConfig().getString(inv + "-inventory.items." + item + ".type").toUpperCase());
            String title = StringManager.formatColorCodes(pC.getConfig().getString(inv + "-inventory.items." + item + ".title"));
            int slot = pC.getConfig().getInt(inv + "-inventory.items." + item + ".slot");
            List<String> lore = pC.getConfig().getStringList(inv + "-inventory.items." + item + ".lore");

            ItemStack itemStack = new ItemStack(mat);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(title);
            meta.setLore(cM.formatCommandVars(StringManager.formatListColorCodes(lore)));
            itemStack.setItemMeta(meta);
            if (pC.getConfig().get(inv + "-inventory.items." + item + ".slot") == null)
                return contents;
            else
                contents.put(slot, itemStack);
        }

        return contents;
    }

    public ItemStack getActionitem(String action) {
        ItemStack item = new ItemStack(Material.getMaterial(pC.getConfig().getString("action-select-inventory.items.action-type.type").toUpperCase()));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringManager.formatColorCodes(pC.getConfig().getString("action-select-inventory.items.action-type.title").replace("?ACTION?", action)));
        List<String> lore = new ArrayList<>();
        for (String s : pC.getConfig().getStringList("action-select-inventory.items.action-type.lore")) {
            lore.add(StringManager.formatColorCodes(s.replace("?ACTION?", action)));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public String getConfigItemName(String inv, String item) {
        return StringManager.formatColorCodes(pC.getConfig().getString(inv + "-inventory.items." + item + ".title"));
    }


}
