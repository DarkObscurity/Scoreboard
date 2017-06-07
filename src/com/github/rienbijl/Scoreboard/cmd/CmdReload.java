package com.github.rienbijl.Scoreboard.cmd;

import com.github.rienbijl.Scoreboard.ConfigControl;
import com.github.rienbijl.Scoreboard.SBUtil;
import com.github.rienbijl.Scoreboard.board.Board;
import com.github.rienbijl.Scoreboard.board.Updater;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Rien on 6-6-2017.
 */
public class CmdReload extends BoardCommand {


    @Override
    public void run(CommandSender sender, String[] args) {

        if(hasPermission(sender, "scoreboard.cmd.reload"))
        {
            ConfigControl.get().reloadConfigs();

            Bukkit.getScheduler().cancelTask(Updater.updater);
            Board.boards.clear();
            Board.btp.clear();
            Board.disabled.clear();

            for(Player p : Bukkit.getOnlinePlayers())
                new Board(p, "main");
//
//            new Updater();

            sender.sendMessage(SBUtil.color("&8[&aScoreboard&8] &aConfiguration reloaded"));

        }

    }

}
