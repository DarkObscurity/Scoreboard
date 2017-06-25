package eu.rhbict.Scoreboard.manager;

import be.maximvdw.placeholderapi.internal.MVdWPlaceholderReplacer;
import eu.rhbict.Scoreboard.core.ScoreboardConfiguration;
import eu.rhbict.Scoreboard.core.ScoreboardCore;
import eu.rhbict.Scoreboard.utill.Hooks;
import eu.rhbict.Scoreboard.utill.Utils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rien on 21-6-2017.
 */
public class BoardPlayer {

    public static String defaultboard = "default";

    private Player holder;
    private String current;

    private Scoreboard cb;
    private ScoreboardCore core;

    private BoardPlayer(Player player)
    {
        map.put(player, this);

        this.holder = player;

        switchBoard(defaultboard);
    }

    public void switchBoard(String board) {
        (cb = Scoreboards.scoreboards.get(board)).puppets.add(this);

        ScoreboardConfiguration config = new ScoreboardConfiguration();
        config.holder = holder;
        config.linecount = cb.lines.size();

        core = new ScoreboardCore(config);

        this.current = board;
        this.update();
    }

    public void disable() {
        Scoreboards.scoreboards.get(current).puppets.remove(this);
        cb = null;
        this.holder.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
    public void enable() {
        switchBoard(defaultboard);
    }

    public void update()
    {
        if(cb != null)
        {

            ArrayList<String> prel = new ArrayList<String>();

            for(Line l : cb.lines)
            {
                String line = l.current;

                if(Hooks.hooks.contains(Hooks.types.PLACEHOLDERAPI))
                    line = PlaceholderAPI.setPlaceholders(this.holder, line);
                if(Hooks.hooks.contains(Hooks.types.MVDWPLACEHOLDERAPI))
                    line = be.maximvdw.placeholderapi.PlaceholderAPI.replacePlaceholders(Bukkit.getOfflinePlayer(this.holder.getUniqueId()), line);

                prel.add(Utils.color(line));

            }

            this.core.setLines(prel);
            this.core.update();
        }
    }


    public static HashMap<Player, BoardPlayer> map = new HashMap<Player, BoardPlayer>();

    public static BoardPlayer getBP(Player p)
    {
        if(map.containsKey(p))
            return map.get(p);
        return new BoardPlayer(p);
    }


}
