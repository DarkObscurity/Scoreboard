package eu.rhbict.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Rien on 22-6-2017.
 */
public class Utils {

    public static String color(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void debug(String msg)
    {
        for(Player p : Bukkit.getOnlinePlayers())
            if(p.hasPermission("scoreboard.debug"))
                p.sendMessage(color("&8[&cScoreboard&8] &7" + msg));
    }

}
