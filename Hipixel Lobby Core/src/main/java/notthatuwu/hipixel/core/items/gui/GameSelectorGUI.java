package notthatuwu.hipixel.core.items.gui;

import notthatuwu.hipixel.core.utils.BungeecordUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

import static org.bukkit.Material.*;

public class GameSelectorGUI implements Listener {

    public void openGui(Player player){
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "All Games");

        //Menu Options(Items)
        ItemStack mainLobby = new ItemStack(BOOKSHELF); //Go to main lobby
        ItemStack bedwars = new ItemStack(Material.getMaterial("BED")); //Go to main lobby

        //Edit the items
        ItemMeta mainLobby_meta = mainLobby.getItemMeta();
        assert mainLobby_meta != null;
        mainLobby_meta.setDisplayName(ChatColor.GREEN + "Main Lobby");
        ArrayList<String> mainLobby_lore = new ArrayList<>();
        mainLobby_lore.add("");
        mainLobby_lore.add(ChatColor.GRAY + "Return to the Main Lobby");
        mainLobby_meta.setLore(mainLobby_lore);
        mainLobby.setItemMeta(mainLobby_meta);

        ItemMeta bedWars_meta = bedwars.getItemMeta();
        assert bedWars_meta != null;
        bedWars_meta.setDisplayName(ChatColor.GREEN + "Bed Wars");
        ArrayList<String> bedwars_lore = new ArrayList<>();
        bedwars_lore.add(ChatColor.DARK_GRAY + "Team Survival");
        bedwars_lore.add("");
        bedwars_lore.add(ChatColor.GRAY + "Protect your bed along with your");
        bedwars_lore.add(ChatColor.GRAY + "teammates and destroy enemy beds");
        bedwars_lore.add(ChatColor.GRAY + "to win !");
        bedwars_lore.add("");
        bedwars_lore.add(ChatColor.GREEN + "> Click to Connect");
        bedWars_meta.setLore(bedwars_lore);
        bedwars.setItemMeta(bedWars_meta);

        //Set slots and register item
        gui.setItem(9, mainLobby);
        gui.setItem(13, bedwars);

        player.openInventory(gui);
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e){
        //Check to see if its the GUI menu
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "All Games")){
            e.setCancelled(true); //So they cant take the items

            Player player = (Player) e.getWhoClicked();
            //Determine what they selected and what to do
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType().equals(Material.getMaterial("BOOKSHELF"))){
                    BungeecordUtil.sendServer(player, "Lobby");
                }
                if (e.getCurrentItem().getType().equals(Material.getMaterial("BED"))){
                    BungeecordUtil.sendServer(player, "Bedwars");
                }
            }
        }

    }

}
