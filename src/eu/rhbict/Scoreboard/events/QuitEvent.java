package eu.rhbict.Scoreboard.events;

import eu.rhbict.Scoreboard.manager.BoardPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Rien-Laptop on 25-6-2017.
 */
public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        BoardPlayer.getBP(e.getPlayer()).disable();
        BoardPlayer.map.remove(e.getPlayer());
    }

}
