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

import static org.bukkit.Material.BOOKSHELF;

public class GameSelectorGUI implements Listener {

    public void openGui(Player player){
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.GRAY + "All Games");

        //Menu Options(Items)
        ItemStack mainLobby = new ItemStack(BOOKSHELF, 1); //Go to main lobby

        //Edit the items
        ItemMeta mainLobby_meta = mainLobby.getItemMeta();
        assert mainLobby_meta != null;
        mainLobby_meta.setDisplayName(ChatColor.GREEN + "Main Lobby");
        ArrayList<String> mainLobby_lore = new ArrayList<>();
        mainLobby_lore.add("");
        mainLobby_lore.add(ChatColor.GRAY + "Return to the Main Lobby");
        mainLobby_meta.setLore(mainLobby_lore);
        mainLobby.setItemMeta(mainLobby_meta);

        //Set slots and register item
        gui.setItem(9, mainLobby);

        player.openInventory(gui);
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e){
        //Check to see if its the GUI menu
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GRAY + "All Games")){
            e.setCancelled(true); //So they cant take the items

            Player player = (Player) e.getWhoClicked();
            //Determine what they selected and what to do
            if (e.getCurrentItem() != null) {
                switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {
                    case BOOKSHELF:
                        BungeecordUtil.sendServer(player, "Lobby");
                        break;
                }
            }
        }

    }

}
