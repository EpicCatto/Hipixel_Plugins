package notthatuwu.hipixel.core.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGamemode implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {


        if (args.length != 0) {
            if (args.length == 1) {
                Player player = (Player) sender;
                if(player.hasPermission("hipixelcore.gamemodes.creative")){
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change your gamemode to " + ChatColor.GOLD + player.getGameMode());
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")) {
                        player.setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change your gamemode to " + ChatColor.GOLD + player.getGameMode());
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change your gamemode to " + ChatColor.GOLD + player.getGameMode());
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change your gamemode to " + ChatColor.GOLD + player.getGameMode());
                    }
                }else {
                    player.sendMessage("&cSorry buddy but seem like you don't have permission to do that.");
                }
            } else {
                Player player = Bukkit.getPlayer(args[1]);
                if (player.hasPermission("hipixelcore.gamemodes.creative.other")) {
                    if (player == null) {
                        sender.sendMessage(ChatColor.RED + "That player is offline");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + " gamemode to " + ChatColor.GOLD + player.getGameMode());
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")) {
                        player.setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + " gamemode to " + ChatColor.GOLD + player.getGameMode());
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + " gamemode to " + ChatColor.GOLD + player.getGameMode());
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        sender.sendMessage(ChatColor.GREEN + "Successfully change " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + " gamemode to " + ChatColor.GOLD + player.getGameMode());
                    }
                }else {
                    player.sendMessage("&cSorry buddy but seem like you don't have permission to do that.");
                }
            }
        }else {
            sender.sendMessage(ChatColor.GREEN + "Usage: /gamemode <mode> <player>");
        }
        return true;
    }
}
