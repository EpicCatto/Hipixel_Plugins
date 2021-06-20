package notthatuwu.hipixel.core;

import notthatuwu.hipixel.core.command.CommandManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;
    public final Logger log = getLogger();
    public CommandManager command;

    @Override
    public void onEnable() {
        instance = this;
        log.info("Core Loaded");
        command = new CommandManager(this);
        super.onEnable();
    }
}
