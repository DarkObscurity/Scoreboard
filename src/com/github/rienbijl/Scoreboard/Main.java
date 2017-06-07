package com.github.rienbijl.Scoreboard;

import com.github.rienbijl.Scoreboard.board.Board;
import com.github.rienbijl.Scoreboard.board.Updater;
import com.github.rienbijl.Scoreboard.cmd.Receiver;
import com.github.rienbijl.Scoreboard.events.JoinEvent;
import com.github.rienbijl.Scoreboard.events.QuitEvent;
import com.github.rienbijl.Scoreboard.events.TeleportEvent;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
        extends JavaPlugin
{
    public static Main instance;

    public static boolean hook_placeholderapi = false;
    public static boolean hook_mvdw = false;

    public static boolean uses_uuid = true;

    public void onEnable()
    {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);


        ConfigControl.get();

//        getCommand("scoreboard").setExecutor(new Receiver());

        if ((Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) && (ConfigControl.get().gc("settings").getBoolean("settings.hooks.placeholderapi")))
        {
            hook_placeholderapi = true;
            SBUtil.info("Detected PlaceholderAPI, hooking in");
        }
        if ((Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) && (ConfigControl.get().gc("settings").getBoolean("settings.hooks.mvdwplaceholderapi")))
        {
            hook_placeholderapi = true;
            SBUtil.info("Detected MVdWPlaceholderAPI, hooking in");
        }

        if (!getConfig().getBoolean("settings.useuuid")) {
            uses_uuid = false;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            new Board(p, "main");
        }
        new Updater();
    }
}
