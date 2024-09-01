package eu.epicraft.com.manager.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GameFrozen {

    private static HashMap<Player, Location> frozenPlayers = new HashMap<>();

    public static Map<Player, Location> getFrozenPlayers() {
        return frozenPlayers;
    }

    public static void freeze(Player player){
        getFrozenPlayers().put(player, player.getLocation());
    }

    public static void unfreeze(Player player){
        getFrozenPlayers().remove(player, player.getLocation());
    }

    public static boolean isFreeze(Player player){
        return getFrozenPlayers().containsKey(player);
    }
}
