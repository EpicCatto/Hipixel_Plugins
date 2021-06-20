package notthatuwu.hipixel.core.command.commands.gamemodes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGamemodeC implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (!player.hasPermission("hipixelcore.gamemodes")) {
                player.sendMessage(ChatColor.RED + "Sorry buddy but seem like you don't have permission to do that.");
            } else {
                if (args.length != 1) {
                    player.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(ChatColor.GREEN + "Successfully change your gamemode to " + ChatColor.GOLD + player.getGameMode());
                    return true;
                }
                if (player.hasPermission("hipixelcore.gamemodes.other")) {
                    Player player2 = Bukkit.getPlayer(args[0]);
                    if (player2 == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is offline"));
                        return true;
                    }
                    player2.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ChatColor.GREEN + "Successfully change " + player2.getName() + " gamemode to " + ChatColor.GOLD + player2.getGameMode());
                }
                if (!player.hasPermission("hipixelcore.gamemodes.other"))
                player.sendMessage(ChatColor.RED + "Sorry buddy but seem like you don't have permission to do that.");
            }
        } else {
            Player player2 = Bukkit.getPlayer(args[0]);
            if (player2 == null) {
                sender.sendMessage(ChatColor.RED + "That player is offline");
                return true;
            }
            sender.sendMessage(ChatColor.GREEN + "Successfully change " + ChatColor.GOLD + player2.getName() + ChatColor.GREEN + " gamemode to " + ChatColor.GOLD + player2.getGameMode());
            player2.setGameMode(GameMode.CREATIVE);
        }
        return true;
    }
}
