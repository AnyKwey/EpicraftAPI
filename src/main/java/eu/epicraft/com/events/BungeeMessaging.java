package eu.epicraft.com.events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.epicraft.com.EpicraftAPI;
import org.bukkit.entity.Player;

/**
 * Created by AnyKwey
 */
public class BungeeMessaging {

    public static void connect(Player player, String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(EpicraftAPI.getInstance(), "BungeeCord", out.toByteArray());
    }
}
