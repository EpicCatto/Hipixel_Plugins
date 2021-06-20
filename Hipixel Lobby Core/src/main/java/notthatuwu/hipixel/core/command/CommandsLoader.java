package notthatuwu.hipixel.core.command;

import notthatuwu.hipixel.core.command.commands.*;
import notthatuwu.hipixel.core.command.commands.gamemodes.CommandGamemodeA;
import notthatuwu.hipixel.core.command.commands.gamemodes.CommandGamemodeC;
import notthatuwu.hipixel.core.command.commands.gamemodes.CommandGamemodeS;
import notthatuwu.hipixel.core.command.commands.gamemodes.CommandGamemodeSP;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandsLoader{
    public void loadCommand(JavaPlugin plugin){
        plugin.getCommand("gamemode").setExecutor(new CommandGamemode());
        plugin.getCommand("survival").setExecutor(new CommandGamemodeS());
        plugin.getCommand("creative").setExecutor(new CommandGamemodeC());
        plugin.getCommand("adventure").setExecutor(new CommandGamemodeA());
        plugin.getCommand("spectator").setExecutor(new CommandGamemodeSP());
    }
}
