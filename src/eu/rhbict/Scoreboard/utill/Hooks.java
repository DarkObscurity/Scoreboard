package eu.rhbict.Scoreboard.utill;

import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * Created by Rien-Laptop on 25-6-2017.
 */
public class Hooks {

    public static ArrayList<types> hooks = new ArrayList<types>();

    public static void load()
    {
        if(ConfigControl.get().gc("settings").getBoolean("hooks.placeholderapi")
                && Bukkit.getPluginManager().isPluginEnabled("PlaceholerAPI"))
            hooks.add(types.PLACEHOLDERAPI);

        if(ConfigControl.get().gc("settings").getBoolean("hooks.mvdwplaceholderapi")
                && Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI"))
            hooks.add(types.MVDWPLACEHOLDERAPI);
    }

    public static enum types {

        PLACEHOLDERAPI,
        MVDWPLACEHOLDERAPI

    }

}
