package notthatuwu.hipixel.core.tablist;

import notthatuwu.hipixel.core.Main;
import notthatuwu.hipixel.core.utils.ChatUtil;
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
    public void playerJoin(PlayerJoinEvent e) {
        setHeaderAndFooter(e.getPlayer());
        (new BukkitRunnable() {
            public void run() {
                Objects.requireNonNull(sb.getTeam(ChatUtil.teamFinder(ChatUtil.placeholders(e.getPlayer(), "%luckperms_highest_group_by_weight%")))).addPlayer(e.getPlayer());
                e.getPlayer().setDisplayName(String.valueOf(Objects.requireNonNull(sb.getTeam(ChatUtil.teamFinder(ChatUtil.placeholders(e.getPlayer(), "%luckperms_highest_group_by_weight%")))).getPrefix()) + e.getPlayer().getName());
                for (Player all : Bukkit.getOnlinePlayers())
                    all.setScoreboard(sb);
                //Main.instance.log.info("ROLE:" +(ChatUtil.teamFinder(ChatUtil.placeholders(e.getPlayer(), "%luckperms_highest_group_by_weight%"))));
           }
        }).runTaskLaterAsynchronously(Main.instance,    5L);
    }

    private Scoreboard sb;

    public void registerRoles(){
        this.sb = Bukkit.getScoreboardManager().getMainScoreboard();
        this.sb.registerNewTeam("01owner");
        this.sb.registerNewTeam("02admin");
        this.sb.registerNewTeam("03mod");
        this.sb.registerNewTeam("04helper");
        this.sb.registerNewTeam("05pig+++");
        this.sb.registerNewTeam("06youtube");
        this.sb.registerNewTeam("07mvp++");
        this.sb.registerNewTeam("08mvp+");
        this.sb.registerNewTeam("09mvp");
        this.sb.registerNewTeam("10vip+");
        this.sb.registerNewTeam("11vip");
        this.sb.registerNewTeam("12default");
        (this.sb.getTeam("01owner")).setPrefix(ChatUtil.formatRoleLmao("owner"));
        (this.sb.getTeam("02admin")).setPrefix(ChatUtil.formatRoleLmao("admin"));
        (this.sb.getTeam("03mod")).setPrefix(ChatUtil.formatRoleLmao("mod"));
        (this.sb.getTeam("04helper")).setPrefix(ChatUtil.formatRoleLmao("helper"));
        (this.sb.getTeam("05pig+++")).setPrefix(ChatUtil.formatRoleLmao("pig+++"));
        (this.sb.getTeam("06youtube")).setPrefix(ChatUtil.formatRoleLmao("youtube"));
        (this.sb.getTeam("07mvp++")).setPrefix(ChatUtil.formatRoleLmao("mvp++"));
        (this.sb.getTeam("08mvp+")).setPrefix(ChatUtil.formatRoleLmao("mvp+"));
        (this.sb.getTeam("09mvp")).setPrefix(ChatUtil.formatRoleLmao("mvp"));
        (this.sb.getTeam("10vip+")).setPrefix(ChatUtil.formatRoleLmao("vip+"));
        (this.sb.getTeam("11vip")).setPrefix(ChatUtil.formatRoleLmao("vip"));
        this.sb.getTeam("12default").setPrefix(ChatUtil.formatRoleLmao("default"));
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
