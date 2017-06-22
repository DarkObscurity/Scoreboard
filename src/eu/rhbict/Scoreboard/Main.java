package eu.rhbict.Scoreboard;

import eu.rhbict.Scoreboard.manager.Scoreboards;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Rien on 20-6-2017.
 */
public class Main extends JavaPlugin implements Listener {

    public static Main instance;

    public void onEnable() {

        long time = System.currentTimeMillis();

        instance = this;

        ConfigControl.get();
        Scoreboards.load();

        Utils.debug("Scoreboard fully enabled in &c" + (System.currentTimeMillis() - time) + "ms");
    }


}
