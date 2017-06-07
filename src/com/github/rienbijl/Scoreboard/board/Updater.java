package com.github.rienbijl.Scoreboard.board;

import com.github.rienbijl.Scoreboard.ConfigControl;
import com.github.rienbijl.Scoreboard.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class Updater
{

    public static int updater = 0;

    public Updater()
    {

        BukkitRunnable run = (new BukkitRunnable()
        {
            public void run()
            {
                for (Board b : Board.boards) {
                    b.update();
                }
            }
        });

        if(ConfigControl.get().gc("settings").getBoolean("settings.lagg-prevention"))
        {
            updater = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.instance, run, 0L, 1L);
        } else {
            updater = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, run, 0L, 1L);
        }

    }
}
