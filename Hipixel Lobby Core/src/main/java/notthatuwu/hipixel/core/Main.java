package notthatuwu.hipixel.core;

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
        super.onEnable();
    }
}
