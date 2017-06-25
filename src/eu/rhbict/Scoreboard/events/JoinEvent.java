package eu.rhbict.Scoreboard.events;

import eu.rhbict.Scoreboard.manager.BoardPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Rien-Laptop on 25-6-2017.
 */
public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        BoardPlayer.getBP(e.getPlayer());
    }

}
