package com.github.rienbijl.Scoreboard.board;

import com.github.rienbijl.Scoreboard.ConfigControl;
import com.github.rienbijl.Scoreboard.Main;
import com.github.rienbijl.Scoreboard.SBUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Board
{
    public static ArrayList<Board> boards = new ArrayList();
    public static ArrayList<Player> disabled = new ArrayList();
    public static HashMap<Player, Board> btp = new HashMap();
    static FileConfiguration config;
    public Scoreboard scoreboard;
    public Objective objective;
    int c;
    int updates;
    Player player;
    ArrayList<String> cache = new ArrayList();
    ArrayList<Line> lines = new ArrayList();

    String board;

    public Board(Player p, String board)
    {

        this.board = board;

        boards.add(this);
        btp.put(p, this);

        config = ConfigControl.get().gc(this.board);

        (new BukkitRunnable() {
            public void run()
            {
                scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                player = p;

                c = (config.getConfigurationSection("board").getKeys(false).size() - 1);
                updates = config.getInt("settings.update-ticks");

                objective = scoreboard.registerNewObjective(p.getName(), "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(SBUtil.color(config.getString("board.title")));

                int score = c;
                int count = 0;
                for (String key : config.getConfigurationSection("board").getKeys(false))
                {
                    int keyInt = 1;
                    try
                    {
                        keyInt = Integer.parseInt(key);
                    }
                    catch (NumberFormatException e)
                    {
                        SBUtil.fatal("Line number " + (count + 1) + " has a non-numeric name.");
                    }
                    if (keyInt != 1)
                    {
                        Team t = scoreboard.registerNewTeam(count + "");
                        t.addEntry(String.valueOf(org.bukkit.ChatColor.values()[count]));

                        objective.getScore(String.valueOf(org.bukkit.ChatColor.values()[count])).setScore(score);

                        count++;
                        score--;
                    }
                    Line l = new Line(player, key);
                    lines.add(l);
                }
                p.setScoreboard(scoreboard);

                update();
            }
        }).runTask(Main.instance);


    }

    String last = "";

    private void title(String s)
    {
        if (this.last == s) {
            return;
        }
        this.last = s;
        this.objective.setDisplayName(s);
    }

    public void update()
    {
        if (disabled.contains(this.player)) {
            return;
        }
        ArrayList<String> newCache = new ArrayList();

        int count = 0;
        boolean first = true;
        for (Line line : this.lines)
        {
            String s = line.next();
            if (first)
            {
                title(s);
                first = false;
            }
            else
            {
                first = false;
                if (Main.hook_placeholderapi) {
                    s = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(this.player, s);
                }
                if (Main.hook_mvdw) {
                    if (Main.uses_uuid) {
                        s = be.maximvdw.placeholderapi.PlaceholderAPI.replacePlaceholders(Bukkit.getOfflinePlayer(this.player.getUniqueId()), s);
                    } else {
                        s = be.maximvdw.placeholderapi.PlaceholderAPI.replacePlaceholders(Bukkit.getOfflinePlayer(this.player.getName()), s);
                    }
                }
                newCache.add(s);
                if (this.cache.contains(s))
                {
                    count++;
                }
                else
                {
                    if (s.length() > 31) {
                        s.substring(31);
                    }
                    if (s.length() > 16)
                    {
                        String last = "";
                        for (int i = 0; i < s.substring(0, 16).length(); i++)
                        {
                            char c = s.charAt(i);
                            if (c == '§') {
                                last = last + "§" + s.charAt(i + 1);
                            }
                        }
                        String s1 = s.substring(0, 16);
                        String s2 = s.substring(16, s.length());
                        if (s2.length() > 15) {
                            s2 = s.substring(0, 15);
                        }
                        if (!last.isEmpty()) {
                            s2 = last + s2;
                        } else {
                            s2 = "&f" + s2;
                        }
                        int finalCount = count;


                        String finalS = s2;
                        new BukkitRunnable() {
                            public void run()
                            {
                                Team t = scoreboard.getTeam(finalCount + "");
                                t.setPrefix(SBUtil.color(s1));
                                t.setSuffix(SBUtil.color(finalS));
                            }
                        }.runTask(Main.instance);
                    }
                    else
                    {
                        int finalCount1 = count;
                        String finalS1 = s;
                        (new BukkitRunnable() {
                            public void run() {
                                Team t = scoreboard.getTeam(finalCount1 + "");
                                t.setPrefix(SBUtil.color(finalS1));
                                t.setSuffix("");
                            }
                        }).runTask(Main.instance);
                    }
                    count++;
                }
            }
        }
        this.cache = newCache;
    }
}
