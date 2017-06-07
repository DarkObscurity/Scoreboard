package com.github.rienbijl.Scoreboard.cmd;

import com.github.rienbijl.Scoreboard.SBUtil;
import org.bukkit.command.CommandSender;

/**
 * Created by Rien on 6-6-2017.
 */
public abstract class BoardCommand {

    public abstract void run(CommandSender sender, String[] args);

    public boolean hasPermission(CommandSender sender, String perms)
    {
        if(!sender.hasPermission(perms))
        {
            sender.sendMessage(SBUtil.color("&8[&aScoreboard&8] &aInsufficient permission"));
            return false;
        }
        return true;
    }

}
