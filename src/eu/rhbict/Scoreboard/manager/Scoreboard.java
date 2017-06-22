package eu.rhbict.Scoreboard.manager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;

/**
 * Created by Rien on 22-6-2017.
 */
public class Scoreboard {

    private YamlConfiguration config;

    ArrayList<Line> lines = new ArrayList<Line>();

    public Scoreboard(YamlConfiguration config)
    {
        this.config = config;

    }



}
