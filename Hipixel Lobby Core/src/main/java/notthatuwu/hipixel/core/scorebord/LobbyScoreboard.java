package notthatuwu.hipixel.core.scorebord;

import me.clip.placeholderapi.PlaceholderAPI;
import notthatuwu.hipixel.core.Main;
import notthatuwu.hipixel.core.tablist.LobbyTabList;
import notthatuwu.hipixel.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import static notthatuwu.hipixel.core.utils.ChatUtil.placeholders;
import static notthatuwu.hipixel.core.utils.ChatUtil.translateColor;

public class LobbyScoreboard implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        if (e.getPlayer().getWorld().getName().equals("world")) {
            final Player p = e.getPlayer();
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
                public void run() {
                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    assert manager != null;
                    final Scoreboard board = manager.getNewScoreboard();
                    final Objective objective = board.registerNewObjective("test", "dummy");
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    objective.setDisplayName(translateColor("&e&lHipixel"));
                    Score score = objective.getScore(translateColor("&7 &7 &7"));
                    score.setScore(10);
                    Score score1 = objective.getScore(translateColor("&rOnline Players&7: &a" + placeholders(e.getPlayer(), "%bungee_total%")));
                    score1.setScore(9);
                    Score score2 = objective.getScore(translateColor("&rYour Rank&7: " + ChatUtil.formatRole(placeholders(e.getPlayer(), "%luckperms_highest_group_by_weight%"))));
                    score2.setScore(8);
                    Score score3 = objective.getScore(translateColor("&7 &7 &7"));
                    score3.setScore(7);
                    Score score4 = objective.getScore(translateColor("&ehipixel.xyz"));
                    score4.setScore(6);
                    p.setScoreboard(board);
                }
            }, 0, 40);
        }
    }
}
