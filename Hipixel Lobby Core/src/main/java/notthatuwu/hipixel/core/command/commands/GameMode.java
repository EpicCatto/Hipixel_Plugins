package notthatuwu.hipixel.core.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameMode implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        Player player = (Player) sender;

        if (player.hasPermission("hipixelcore.gamemodes")){
            if (command.getName().equalsIgnoreCase("gamemode c") || command.getName().equalsIgnoreCase("gamemode creative") || command.getName().equalsIgnoreCase("gmc")){
                player.setGameMode(org.bukkit.GameMode.CREATIVE);
                player.sendMessage(ChatColor.GOLD + "Successfully Change your gamemode to " + ChatColor.GREEN + "Creative");
            }
        }else {
            player.sendMessage(ChatColor.RED + "Sorry but you don't have permission to do this");
        }
        return true;
    }
}
