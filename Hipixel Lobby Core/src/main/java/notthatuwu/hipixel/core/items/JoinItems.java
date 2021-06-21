package notthatuwu.hipixel.core.items;

import notthatuwu.hipixel.core.items.gui.GameSelectorGUI;
import notthatuwu.hipixel.core.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JoinItems implements Listener {

    private static ItemStack compass;

    public static void init(){
        //Select Game compass
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Game Menu " + ChatColor.GRAY + "(Right Click)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right click this item to select a server");
        meta.setLore(lore);
        item.setItemMeta(meta);
        compass = item;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().setItem(0, compass);
    }

    @EventHandler
    public void onDead(PlayerDeathEvent event) {
        event.getDrops().remove(compass);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getWorld().getName().equals("world")) {
            event.getPlayer().getInventory().setItem(0, compass);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event){
        if (event.getPlayer().getWorld().getName().equals("world")) {
            if (event.getItemDrop().getItemStack().equals(compass)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event) {
        if (event.getCurrentItem().equals(compass)) {
            event.setCancelled(true); //So they cant take the items
        }
    }

    @EventHandler
    public void interactEvent(PlayerInteractEvent event) {
        event.getAction();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(Objects.equals(event.getItem(), compass)){
                GameSelectorGUI game = new GameSelectorGUI();
                game.openGui(event.getPlayer());
            }
        }
    }

}
