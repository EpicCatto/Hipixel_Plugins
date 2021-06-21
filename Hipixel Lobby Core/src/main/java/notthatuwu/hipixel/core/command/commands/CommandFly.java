package notthatuwu.hipixel.core.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandFly implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!sender.hasPermission("hipixelcore.gamemodes")){
            sender.sendMessage(ChatColor.RED + "Sorry buddy but seem like you don't have permission to do that.");
            return true;
        }else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (player.hasPermission("hipixelcore.fly")) {
                        if (player.getAllowFlight()) {
                            player.setFlying(false);
                            player.setAllowFlight(false);
                            player.sendMessage(ChatColor.RED + "Disabled fly mode!");
                        } else {
                            player.setAllowFlight(true);
                            player.setFlySpeed(0.1F);
                            player.sendMessage(ChatColor.GREEN + "Enabled fly mode!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Sorry buddy but seem like you don't have permission to do that.");
                    }
                } else if (args.length == 1) {
                    if (player.hasPermission("hipixelcore.fly.other")) {
                        Player otherplayer = Bukkit.getPlayer(args[0]);
                        if (otherplayer != null) {
                            if (otherplayer.getAllowFlight()) {
                                otherplayer.setFlying(false);
                                otherplayer.setAllowFlight(false);
                                otherplayer.sendMessage(ChatColor.RED + "Disabled fly mode!");
                                player.sendMessage(ChatColor.RED + args[0] +
                                        " fly was disabled");
                            } else {
                                otherplayer.setAllowFlight(true);
                                otherplayer.setFlySpeed(0.1F);
                                otherplayer.sendMessage(ChatColor.GREEN + "Enabled fly mode!");
                                player.sendMessage(ChatColor.GREEN + args[0] +
                                        " fly was enabled");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + args[0] +
                                    " is not online or doesn't exist!");
                        }
                    }else {
                        sender.sendMessage(ChatColor.RED + "Sorry buddy but seem like you don't have permission to do that.");
                    }
                }
            }
        }
        return true;
    }
}
