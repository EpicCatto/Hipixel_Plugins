package notthatuwu.hipixel.core;

import notthatuwu.hipixel.core.command.CommandsLoader;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;
    public final Logger log = getLogger();

    @Override
    public void onEnable() {
        instance = this;
        log.info("Core Loaded");
        CommandsLoader commandsLoader = new CommandsLoader();
        commandsLoader.loadCommand(this);
        //getServer().getPluginCommand(getDescription().getCommands().keySet().toArray()[0].toString()).setExecutor(new CommandGamemodeS());
        super.onEnable();
    }
}
