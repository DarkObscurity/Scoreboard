package eu.rhbict.Scoreboard.manager;

import eu.rhbict.Scoreboard.utill.ConfigControl;
import eu.rhbict.Scoreboard.Main;
import eu.rhbict.Scoreboard.utill.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Rien on 20-6-2017.
 */
public class Scoreboards {

    public static HashMap<String, FileConfiguration> boards = new HashMap<String, FileConfiguration>();
    public static HashMap<String, Scoreboard> scoreboards = new HashMap<String, Scoreboard>();

    public static void load()
    {

        BoardPlayer.defaultboard = ConfigControl.get().gc("settings").getString("settings.default-scoreboard");

        File data = Main.instance.getDataFolder();
        File[] configs = data.listFiles();

        if(configs != null)
        {
            for(File config : configs)
            {
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(config);
                if(yaml.isConfigurationSection("board"))
                {
                    String name = config.getName().replaceFirst("board_", "").replaceAll(".yml", "");

                    boards.put(name, yaml);
                    scoreboards.put(name, new Scoreboard(yaml));

                    Utils.debug("Loaded and initialized board &c'" + name + "'");
                }
            }
        }
    }

}
