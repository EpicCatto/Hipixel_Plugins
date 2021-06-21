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

    public static String teamFinder(String role){
        String formattedRole = null;
        if (role.equalsIgnoreCase("default")){
            formattedRole = "12default";
        }else
        if (role.equalsIgnoreCase("vip")){
            formattedRole = "11vip";
        }else
        if (role.equalsIgnoreCase("vip+")){
            formattedRole = "10vip";
        }else
        if (role.equalsIgnoreCase("mvp")){
            formattedRole = "09mvp";
        }else
        if (role.equalsIgnoreCase("mvp+")){
            formattedRole = "08mvp+";
        }else
        if (role.equalsIgnoreCase("mvp++")){
            formattedRole = "07mvp++";
        }else
        if (role.equalsIgnoreCase("youtube")){
            formattedRole = "06youtube";
        }else
        if (role.equalsIgnoreCase("pig+++")){
            formattedRole = "05pig+++";
        }else
        if (role.equalsIgnoreCase("helper")){
            formattedRole = "04helper";
        }else
        if (role.equalsIgnoreCase("mod")){
            formattedRole = "03mod";
        }else
        if (role.equalsIgnoreCase("admin")){
            formattedRole = "02admin";
        }else
        if (role.equalsIgnoreCase("owner")){
            formattedRole = "01owner";
        }else{
            formattedRole = "Not Found!";
        }
        return formattedRole;
    }

    public static String formatRoleLmao(String role){
        String formattedRole = null;
        if (role.equalsIgnoreCase("default")){
            formattedRole = "&7";
        }
        if (role.equalsIgnoreCase("vip")){
            formattedRole = "[&aVIP] ";
        }
        if (role.equalsIgnoreCase("vip+")){
            formattedRole = "[&aVIP&e+&a] ";
        }
        if (role.equalsIgnoreCase("mvp")){
            formattedRole = "[&bMVP] ";
        }
        if (role.equalsIgnoreCase("mvp+")){
            formattedRole = "[&bMVP&e+&b] ";
        }
        if (role.equalsIgnoreCase("mvp++")){
            formattedRole = "[&bMVP&e++&b] ";
        }
        if (role.equalsIgnoreCase("youtube")){
            formattedRole = "[&cYOUTUBE] ";
        }
        if (role.equalsIgnoreCase("pig+++")){
            formattedRole = "[&dPIG&b+++&d] ";
        }
        if (role.equalsIgnoreCase("helper")){
            formattedRole = "[&9HELPER] ";
        }
        if (role.equalsIgnoreCase("mod")){
            formattedRole = "[&2MOD] ";
        }
        if (role.equalsIgnoreCase("admin")){
            formattedRole = "[&cADMIN] ";
        }
        if (role.equalsIgnoreCase("owner")){
            formattedRole = "[&cOWNER] ";
        }
        return formattedRole;
    }

    public static String roleNameColor(String role){
        String formattedRole = null;
        if (role.equalsIgnoreCase("default")){
            formattedRole = "&7";
        }
        if (role.equalsIgnoreCase("vip")){
            formattedRole = "&a";
        }
        if (role.equalsIgnoreCase("vip+")){
            formattedRole = "&a";
        }
        if (role.equalsIgnoreCase("mvp")){
            formattedRole = "&b";
        }
        if (role.equalsIgnoreCase("mvp+")){
            formattedRole = "&b";
        }
        if (role.equalsIgnoreCase("mvp++")){
            formattedRole = "&b";
        }
        if (role.equalsIgnoreCase("youtube")){
            formattedRole = "&c";
        }
        if (role.equalsIgnoreCase("pig+++")){
            formattedRole = "&d";
        }
        if (role.equalsIgnoreCase("helper")){
            formattedRole = "&9";
        }
        if (role.equalsIgnoreCase("mod")){
            formattedRole = "&2";
        }
        if (role.equalsIgnoreCase("admin")){
            formattedRole = "&c";
        }
        if (role.equalsIgnoreCase("owner")){
            formattedRole = "&c";
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
