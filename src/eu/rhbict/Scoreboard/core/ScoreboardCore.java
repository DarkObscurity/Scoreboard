package eu.rhbict.Scoreboard.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rien on 20-6-2017.
 */
public class ScoreboardCore {

    // Customizable
    private ArrayList<String> lines = new ArrayList<String>();
    private Scoreboard board = null;
    private Objective objective = null;
    private String title = "Scoreboard";
    private ScoreboardConfiguration config = null;

    // Not interactable
    private ArrayList<String> cache_level1 = new ArrayList<String>();
    private boolean isInitialized = false;

    public ScoreboardCore(ScoreboardConfiguration config)
    {
        this.board = Bukkit.getScoreboardManager().getNewScoreboard();
        this.config = config;

        this.objective = this.board.registerNewObjective("scoreboardcore", "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName(this.title);

        int score = config.linecount;
        for (int i = 1; i <= config.linecount; i++)
        {
            Team t = this.board.registerNewTeam(i + "");
            t.addEntry(ChatColor.values()[i].toString());

            objective.getScore(ChatColor.values()[i].toString()).setScore(score);

            score--;
        }

        this.config.holder.setScoreboard(this.board);
    }

    // Getters
    public List<String> getLines() { return this.lines; }
    public ScoreboardConfiguration getConfig() { return this.config; }
    public Scoreboard getScoreboard() { return this.board; }

    // Setters
    public void setLines(ArrayList<String> lines) { this.lines = lines; }
    public void setTitle(String title) { this.title = title; }

    // Actions
    public void purgeCache() { this.cache_level1.clear(); }

    public void update()
    {

        this.objective.setDisplayName(this.title);
        ArrayList<String> cache_level2 = new ArrayList<String>();

        int count = 0;
        for(String line : this.lines)
        {
            cache_level2.add(line);

            if(cache_level1.contains(line))
                continue;

            line = prepapareColors(line);

            ArrayList<String> parts = getParts(line);
            Team t = this.board.getTeam((count+1) + "");

            String colors = getLastColor(parts.get(0));

            t.setPrefix(parts.get(0));
            t.setSuffix(parts.get(1));

            count++;
        }

        cache_level1 = cache_level2;
    }

    // Utilities
    private ArrayList<String> getParts(String s)
    {

        ArrayList<String> parts = new ArrayList<String>();

        if(ChatColor.stripColor(s).length() > 16)
        {
            parts.add(s.substring(0, 16));

            String s2 = s.substring(16, s.length());
            if(s2.length() > 16)
                s2 = s2.substring(0, 16);
            parts.add(s2);
        } else {
            parts.add(s);
            parts.add("");
        }

        return parts;

    }

    private String prepapareColors(String color)
    {
        ArrayList<String> parts = getParts(color);
        return parts.get(0) + "§f" + getLastColor(parts.get(0)) + parts.get(1);

    }

    private String getLastColor(String s)
    {
        String last = ChatColor.getLastColors(s);
        if(last == null)
            return "";
        return last;
    }

}
