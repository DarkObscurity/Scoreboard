package eu.rhbict.Scoreboard.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Rien on 21-6-2017.
 */
public class BoardPlayer {

    private Player holder;
    private String current;

    private BoardPlayer(Player player)
    {
        map.put(player, this);

        this.holder = player;
    }

    public void switchBoard(String board) {}
    public void disable() {}
    public void enable() {}


    static HashMap<Player, BoardPlayer> map = new HashMap<Player, BoardPlayer>();

    public BoardPlayer getBP(Player p)
    {
        if(map.containsKey(p))
            return map.get(p);
        return new BoardPlayer(p);
    }


}
