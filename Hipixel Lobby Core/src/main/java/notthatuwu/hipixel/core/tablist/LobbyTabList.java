package notthatuwu.hipixel.core.tablist;

import notthatuwu.hipixel.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class LobbyTabList implements Listener {

    //server package, such as "v1_16_R3"
    private String serverPackage;

    //server minor version such as "16"
    public int minorVersion;

    public LobbyTabList(){
        serverPackage = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        minorVersion = Integer.parseInt(serverPackage.split("_")[1]);
    }

    @EventHandler
    public void onChat(PlayerJoinEvent e) {
        setHeaderAndFooter(e.getPlayer());
    }

    public void setHeaderAndFooter(Player player) {
        try {
            Object headerJson = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.AQUA + "You are playing on " + ChatColor.YELLOW + ChatColor.BOLD +  "HIPIXEL.XYZ\"}");
            Object footerJson = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.GREEN + "Ranks, Boosters & MORE! " + ChatColor.AQUA +  "store.hipixel.xyz\"}");
            Object packet = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNMSClass("IChatBaseComponent")).newInstance(headerJson);

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footerJson);

            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (IllegalArgumentException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class<?> getNMSClass(String... names) throws ClassNotFoundException {
        for (String name : names) {
            try {
                return getNMSClass(name);
            } catch (ClassNotFoundException e) {
            }
        }
        throw new ClassNotFoundException("No class found with possible names " + Arrays.toString(names));
    }
    /**
     * Returns class from given name
     * @param name - class name
     * @return class from given name
     * @throws ClassNotFoundException if class was not found
     */
    private Class<?> getNMSClass(String name) throws ClassNotFoundException {
        if (minorVersion >= 17) {
            return Class.forName(name);
        } else {
            try {
                return Class.forName("net.minecraft.server." + serverPackage + "." + name);
            } catch (ClassNotFoundException e) {
                //modded server?
                return Main.class.getClassLoader().loadClass("net.minecraft.server." + serverPackage + "." + name);
            } catch (NullPointerException e) {
                //nested class in modded server
                throw new ClassNotFoundException(name);
            }
        }
    }
}
