package notthatuwu.hipixel.core.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static String translateColor(String input){
        return ChatColor.translateAlternateColorCodes('&',input);
    }

    public static String placeholders(Player player, String content) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") &&
                PlaceholderAPI.containsPlaceholders(content))
            return PlaceholderAPI.setPlaceholders(player, content);
        return content;
    }
}
