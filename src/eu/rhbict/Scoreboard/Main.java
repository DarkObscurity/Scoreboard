package eu.rhbict.Scoreboard;

import eu.rhbict.Scoreboard.core.ScoreboardConfiguration;
import eu.rhbict.Scoreboard.core.ScoreboardCore;
import eu.rhbict.Scoreboard.events.JoinEvent;
import eu.rhbict.Scoreboard.events.QuitEvent;
import eu.rhbict.Scoreboard.manager.BoardPlayer;
import eu.rhbict.Scoreboard.manager.Scoreboards;
import eu.rhbict.Scoreboard.utill.ConfigControl;
import eu.rhbict.Scoreboard.utill.Hooks;
import eu.rhbict.Scoreboard.utill.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rien on 20-6-2017.
 */
public class Main extends JavaPlugin {

    public static Main instance;

    public void onEnable() {

        long time = System.currentTimeMillis();

        instance = this;

        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);

        ConfigControl.get();

        Scoreboards.load();
        Hooks.load();

        loadPlayers();

        Utils.debug("Scoreboard fully enabled in &c" + (System.currentTimeMillis() - time) + "ms");

    }

    private void loadPlayers()
    {
        for(Player p : Bukkit.getOnlinePlayers())
            BoardPlayer.getBP(p);
    }

}
