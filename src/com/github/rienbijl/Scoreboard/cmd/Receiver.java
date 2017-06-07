package com.github.rienbijl.Scoreboard.cmd;

import com.github.rienbijl.Scoreboard.Main;
import com.github.rienbijl.Scoreboard.SBUtil;
import com.github.rienbijl.Scoreboard.board.Board;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.ScoreboardManager;

public class Receiver
        implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Scoreboard commands may only be issued by a player.");
        }
        else
        {
            Player p = (Player)sender;
            if (args.length < 1)
            {
                p.sendMessage(SBUtil.color("&8[&aScoreboard&8] Command overview"));
                p.sendMessage(SBUtil.color("&4/sb reload :&f reload configuration"));
            }
            else
            {
                if(args[0].equalsIgnoreCase("reload"))
                {
                    new CmdReload().run(sender, args);
                }

                else {
                    p.sendMessage(SBUtil.color("&8[&aScoreboard&8] Command overview"));
                    p.sendMessage(SBUtil.color("&4/sb reload :&f reload configuration"));
                }
            }
        }
        return true;
    }
}