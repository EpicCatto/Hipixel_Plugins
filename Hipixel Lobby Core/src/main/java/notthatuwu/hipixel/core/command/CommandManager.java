package notthatuwu.hipixel.core.command;

import notthatuwu.hipixel.core.Main;
import notthatuwu.hipixel.core.command.commands.GameMode;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CommandManager {

    public CommandManager(JavaPlugin plugin){
        Objects.requireNonNull(plugin.getCommand("gamemode")).setExecutor(new GameMode());
        Objects.requireNonNull(plugin.getCommand("gmc")).setExecutor(new GameMode());
        Main.instance.log.info("Successfully load Commands");
    }

}
