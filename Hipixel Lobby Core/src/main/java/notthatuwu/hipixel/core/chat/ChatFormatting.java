package notthatuwu.hipixel.core.chat;

import notthatuwu.hipixel.core.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatting implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("hipixelcore.chatcolor"))
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        String format = "{RANK_COLOR}{RANKWITH[]}&r{RANK_COLOR}{PLAYER_NAME}&r: &r{MESSAGE}";
        format = format.replace("{RANKWITH[]}", ChatUtil.formatRoleLmao(ChatUtil.placeholders(p, "%luckperms_highest_group_by_weight%")));
        format = format.replace("{RANK}", ChatUtil.formatRole(ChatUtil.placeholders(p, "%luckperms_highest_group_by_weight%")));
        format = format.replace("{RANK_COLOR}", ChatUtil.roleNameColor(ChatUtil.placeholders(p, "%luckperms_highest_group_by_weight%")));

        format = format.replace("{PLAYER_NAME}", p.getName());
        format = format.replace("{DISPLAY_NAME}", "%s");
        format = ChatUtil.placeholders(p, format);
        format = ChatColor.translateAlternateColorCodes('&', format);
        format = format.replace("%", "%%");
        format = format.replace("{MESSAGE}", "%2$s");
        e.setFormat(format);
    }
}
