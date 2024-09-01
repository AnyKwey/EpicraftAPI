package eu.epicraft.com;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * created by AnyKwey
 */
public class MainBungee extends Plugin {

    public static Map<ProxiedPlayer, ProxiedPlayer> message = new HashMap<ProxiedPlayer, ProxiedPlayer>();

    @Override
    public void onEnable() {
        getProxy().getConsole().sendMessage("§aThe Proxy process is Available!");
        //getProxy().getPluginManager().registerListener(this, new MessagingEvents());
    }

    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage("§cThe Proxy will shutdown!");
    }
}
