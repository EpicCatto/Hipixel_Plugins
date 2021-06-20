package notthatuwu.hipixel.core.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static String translateColor(String input){
        return ChatColor.translateAlternateColorCodes('&',input);
    }

    public static String formatRole(String role){
        String formattedRole = null;
        if (role.equalsIgnoreCase("default")){
            formattedRole = "&7Default";
        }
        if (role.equalsIgnoreCase("vip")){
            formattedRole = "&aVIP";
        }
        if (role.equalsIgnoreCase("vip+")){
            formattedRole = "&aVIP&e+";
        }
        if (role.equalsIgnoreCase("mvp")){
            formattedRole = "&bMVP";
        }
        if (role.equalsIgnoreCase("mvp+")){
            formattedRole = "&bMVP&e+";
        }
        if (role.equalsIgnoreCase("mvp++")){
            formattedRole = "&bMVP&e++";
        }
        if (role.equalsIgnoreCase("youtube")){
            formattedRole = "&cYOUTUBE";
        }
        if (role.equalsIgnoreCase("pig+++")){
            formattedRole = "&dPIG&b+++";
        }
        if (role.equalsIgnoreCase("helper")){
            formattedRole = "&9HELPER";
        }
        if (role.equalsIgnoreCase("mod")){
            formattedRole = "&2MOD";
        }
        if (role.equalsIgnoreCase("admin")){
            formattedRole = "&cADMIN";
        }
        if (role.equalsIgnoreCase("owner")){
            formattedRole = "&cOWNER";
        }
        return formattedRole;
    }

    public static String placeholders(Player player, String content) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") &&
                PlaceholderAPI.containsPlaceholders(content))
            return PlaceholderAPI.setPlaceholders(player, content);
        return content;
    }
}
