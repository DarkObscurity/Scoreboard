package com.github.rienbijl.Scoreboard.board;

import com.github.rienbijl.Scoreboard.ConfigControl;
import com.github.rienbijl.Scoreboard.Main;
import com.github.rienbijl.Scoreboard.SBUtil;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Line
{
    static FileConfiguration config = ConfigControl.get().gc("main");
    ArrayList<String> lines = new ArrayList();
    int iterations_needed;
    int scroll_pos = -1;
    int iterations = 0;
    Player p;
    int entries = 0;

    public Line(Player p, String row)
    {
        this.p = p;
        this.iterations_needed = config.getInt("board." + row + ".update");
        this.iterations = this.iterations_needed;
        for (String s : config.getStringList("board." + row + ".content"))
        {
            this.lines.add(s);
            this.entries += 1;
        }
    }

    public String next()
    {
        this.iterations += 1;
        if (this.iterations >= this.iterations_needed) {
            this.scroll_pos += 1;
        }
        if (this.iterations >= this.iterations_needed) {
            this.iterations = 0;
        }
        if (this.scroll_pos >= this.entries) {
            this.scroll_pos = 0;
        }
        String s = (String)this.lines.get(this.scroll_pos);
        if (Main.hook_placeholderapi) {
            s = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(this.p, s);
        }
        if (Main.hook_mvdw) {
            if (Main.uses_uuid) {
                s = be.maximvdw.placeholderapi.PlaceholderAPI.replacePlaceholders(Bukkit.getOfflinePlayer(this.p.getUniqueId()), s);
            } else {
                s = be.maximvdw.placeholderapi.PlaceholderAPI.replacePlaceholders(Bukkit.getOfflinePlayer(this.p.getName()), s);
            }
        }
        s = SBUtil.color(s);
        return s;
    }
}
