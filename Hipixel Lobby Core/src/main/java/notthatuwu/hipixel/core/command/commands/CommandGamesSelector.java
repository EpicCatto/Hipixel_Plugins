package notthatuwu.hipixel.core.command.commands;

import notthatuwu.hipixel.core.items.gui.GameSelectorGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGamesSelector implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
         if (sender instanceof Player) {
                Player player = (Player) sender;
             GameSelectorGUI select = new GameSelectorGUI();
             select.openGui(player);
            }
        return true;
    }
}
