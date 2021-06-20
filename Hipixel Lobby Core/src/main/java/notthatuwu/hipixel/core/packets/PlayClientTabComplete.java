package notthatuwu.hipixel.core.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;

public class PlayClientTabComplete extends PacketAdapter {
    public PlayClientTabComplete(Plugin plugin, PacketType... types) {
        super(plugin, types);
    }

    public void onPacketReceiving(PacketEvent event) {
        PacketType packetType = event.getPacketType();
        if (packetType.equals(PacketType.Play.Client.TAB_COMPLETE)) {
            if (event.getPlayer().hasPermission("hipixelcore.pluginhider.bypass"))
                return;
            event.setCancelled(true);
            /*PacketContainer packetContainer = event.getPacket();
            String message = ((String) packetContainer.getSpecificModifier(String.class).read(0)).toLowerCase();
            if (message.equals("/") || message.contains(":")) {
                event.setCancelled(true);
                return;
            }
            if (message.toLowerCase().startsWith("/plugins")
                    || message.toLowerCase().startsWith("/version")
                    || message.toLowerCase().startsWith("/about")
                    || message.toLowerCase().startsWith("/bukkit:plugins")
                    || message.toLowerCase().startsWith("/bukkit:pl")
                    || message.toLowerCase().startsWith("/bukkit:ver")
                    || message.toLowerCase().startsWith("/bukkit:version")
                    || message.toLowerCase().startsWith("/bukkit:about")
                    || message.toLowerCase().startsWith("/bukkit:?")
                    || message.toLowerCase().startsWith("/bukkit:help")
                    || message.toLowerCase().startsWith("/icanhasbukkit")
                    || message.toLowerCase().startsWith("/pl")
                    || message.toLowerCase().startsWith("/plugins"))
                event.setCancelled(true);*/
        }
    }
}