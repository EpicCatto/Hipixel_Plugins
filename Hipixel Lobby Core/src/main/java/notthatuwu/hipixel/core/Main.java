package notthatuwu.hipixel.core;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketListener;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import notthatuwu.hipixel.core.chat.ChatFormatting;
import notthatuwu.hipixel.core.command.CommandsLoader;
import notthatuwu.hipixel.core.items.JoinItems;
import notthatuwu.hipixel.core.items.gui.GameSelectorGUI;
import notthatuwu.hipixel.core.packets.PlayClientTabComplete;
import notthatuwu.hipixel.core.scorebord.LobbyScoreboard;
import notthatuwu.hipixel.core.tablist.LobbyTabList;
import notthatuwu.hipixel.core.utils.BungeeChannelApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {

    public static Main instance;
    public final Logger log = getLogger();


    @Override
    public void onEnable() {
        instance = this;
        CommandsLoader commandsLoader = new CommandsLoader();
        commandsLoader.loadCommand(this);

        //Connect to the bungeecord
        BungeeChannelApi api = BungeeChannelApi.of(this); // this = Plugin instance.

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        //Scoreborad
        getServer().getPluginManager().registerEvents(new LobbyScoreboard(), this);

        //ChatFormatting
        getServer().getPluginManager().registerEvents(new ChatFormatting(), this);

        //TabList
        new LobbyTabList();
        LobbyTabList tab = new LobbyTabList();
        try{
            tab.registerRoles();
        }catch (Exception e){
            //e.printStackTrace();
        }
        getServer().getPluginManager().registerEvents(new LobbyTabList(), this);

        //CustomGui And JoinItem
        getServer().getPluginManager().registerEvents(new GameSelectorGUI(), this);
        JoinItems.init();
        getServer().getPluginManager().registerEvents(new JoinItems(), this);

        //Anti Plugin Leak
        getServer().getPluginManager().registerEvents(this, this);
        ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)new PlayClientTabComplete((Plugin)this, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }));
        //Lmao
        log.info("Core Loaded");
        super.onEnable();
    }

    // Anti Plugins steal
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandPlugins(PlayerCommandPreprocessEvent event) {
        if (!event.getPlayer().hasPermission("hipixelcore.pluginhider.bypass")
        && event.getMessage().toLowerCase().startsWith("/plugins")
        || event.getMessage().toLowerCase().startsWith("/version")
        || event.getMessage().toLowerCase().startsWith("/about")
        || event.getMessage().toLowerCase().startsWith("/bukkit:plugins")
        || event.getMessage().toLowerCase().startsWith("/bukkit:pl")
        || event.getMessage().toLowerCase().startsWith("/bukkit:ver")
        || event.getMessage().toLowerCase().startsWith("/bukkit:version")
        || event.getMessage().toLowerCase().startsWith("/bukkit:about")
        || event.getMessage().toLowerCase().startsWith("/bukkit:?")
        || event.getMessage().toLowerCase().startsWith("/bukkit:help")
        || event.getMessage().toLowerCase().startsWith("/icanhasbukkit")
        || event.getMessage().toLowerCase().startsWith("/pl")
        || event.getMessage().toLowerCase().startsWith("/plugins")
        || event.getMessage().toLowerCase().startsWith("/help")
        || event.getMessage().toLowerCase().startsWith("/?")
        ){
            event.setCancelled(true);
            event.getPlayer().sendMessage(" \n" + ChatColor.GREEN + "-------------------" + ChatColor.YELLOW + ChatColor.BOLD + " Hipixel " + ChatColor.RESET + ChatColor.GREEN + "-------------------"
            + "\n" + ChatColor.GOLD + "This server is using Custom/own plugins."
            + "\n" + ChatColor.LIGHT_PURPLE + "https://github.com/NotThatUwU/Hipixel_Plugins"
            + "\n" + ChatColor.GOLD + "You maybe find what are you looking there."
            + "\n" + ChatColor.GREEN + "----------------------------------------------" + "\n ");
        }
    }
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        e.getPlayer().setAllowFlight(false);
        e.getPlayer().setFlying(false);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);


    }
}
