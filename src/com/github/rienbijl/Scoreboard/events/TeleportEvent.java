package com.github.rienbijl.Scoreboard.events;

import com.github.rienbijl.Scoreboard.Main;
import com.github.rienbijl.Scoreboard.board.Board;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scoreboard.ScoreboardManager;

public class TeleportEvent
        implements Listener
{
    @EventHandler
    public void onTeleport(PlayerTeleportEvent e)
    {
        List<String> disabled = Main.instance.getConfig().getStringList("disabled_worlds");
        if (disabled != null)
        {
            boolean isDisabled = false;
            for (String world : disabled) {
                if (world.toLowerCase().equals(e.getPlayer().getLocation().getWorld().getName().toLowerCase()))
                {
                    isDisabled = true;

                    Board.disabled.add(e.getPlayer());
                    Board b = (Board)Board.btp.get(e.getCause());
                    Board.btp.remove(b);
                    Board.boards.remove(b);
                    e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                }
            }
            if ((!isDisabled) && (Board.disabled.contains(e.getPlayer())))
            {
//                Board.disabled.remove(e.getPlayer());
//                new Board(e.getPlayer());
            }
        }
    }
}
