package notthatuwu.hipixel.core.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSpeed implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /speed <value>");
                return true;
            }
            if (args.length == 1) {
                double speed = 1;
                try {
                    speed = Double.parseDouble(args[0]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Wrong number format");
                    return true;
                }
                if ((player.hasPermission("hipixelcore.speed"))) {
                    if (speed < 0 || speed > 5) {
                        player.sendMessage(ChatColor.RED + "Speed can't be less than 0 or more than 5");
                    } else {
                        player.setFlySpeed((float) (speed / 5f));
                        player.setWalkSpeed((float) (speed / 5f));
                        player.sendMessage(ChatColor.GREEN + "Speed set to " + speed);
                    }
                    return true;
                }else {
                    sender.sendMessage(ChatColor.RED + "Sorry buddy but seem like you don't have permission to do that.");
                }
            }
        }
        return true;
    }
}
