package eu.rhbict.Scoreboard.manager;

import eu.rhbict.Scoreboard.Main;
import eu.rhbict.Scoreboard.utill.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rien on 22-6-2017.
 */
public class Scoreboard {

    private YamlConfiguration config;

    ArrayList<Line> lines = new ArrayList<Line>();
    ArrayList<BoardPlayer> puppets = new ArrayList<BoardPlayer>();

    public Scoreboard(YamlConfiguration config)
    {
        this.config = config;

        int count = 0;

        for(String key : config.getConfigurationSection("board").getKeys(false))
        {
            int keyInt = -1;
            try {
                keyInt = Integer.parseInt(key);
            } catch (Exception ex)
            {
                Utils.debug("FATAL: Line number " + (count + 1) + " has a non-numeric name");
            }

            int update = config.getInt("board." + key + ".update");
            List<String> lines = config.getStringList("board." + key + ".content");

            Line l = new Line(lines, update);

            this.lines.add(l);

            count++;
        }

        this.loop();

    }

    public void loop()
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new BukkitRunnable() {
            @Override
            public void run() {

                for(Line l : lines)
                    l.next();

                for(BoardPlayer bp : puppets)
                    bp.update();

            }
        }, 0L, 1L);
    }





}
