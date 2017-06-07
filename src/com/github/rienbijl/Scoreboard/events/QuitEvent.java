package com.github.rienbijl.Scoreboard.events;

import com.github.rienbijl.Scoreboard.board.Board;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.ScoreboardManager;

public class QuitEvent
        implements Listener
{
    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        Board.boards.remove(e.getPlayer());
    }
}
