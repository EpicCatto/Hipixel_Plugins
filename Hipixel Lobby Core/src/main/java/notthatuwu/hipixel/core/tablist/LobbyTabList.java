package notthatuwu.hipixel.core.tablist;

import notthatuwu.hipixel.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class LobbyTabList implements Listener {

    public void drawTabList(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
            public void run() {
                Scoreboard scoreboard = Main.instance.getServer().getScoreboardManager().getMainScoreboard();
                Team team = scoreboard.getTeam("team");
                if (team == null){
                    team = scoreboard.registerNewTeam("team");
                }
                team.setPrefix("&c&RANK HERE &7");

                for (Player player : Main.instance.getServer().getOnlinePlayers()){
                    team.addEntry(player.getName());
                }

            }
        }, 0, 40);
    }

}
