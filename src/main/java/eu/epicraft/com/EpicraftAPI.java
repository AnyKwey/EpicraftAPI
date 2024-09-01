package eu.epicraft.com;

import eu.epicraft.com.data.mysql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * created by AnyKwey
 */
public class EpicraftAPI extends JavaPlugin {

    private static EpicraftAPI instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        MySQL.connect(getConfig().getString("mysql.host"), getConfig().getInt("mysql.port"), getConfig().getString("mysql.database"), getConfig().getString("mysql.user"), getConfig().getString("mysql.password"));
        MySQL.createTables();
        getServer().getConsoleSender().sendMessage("§aThe plugin is available!");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
        getServer().getConsoleSender().sendMessage("§aThe plugin is stopped correctly!");
    }

    public static EpicraftAPI getInstance() {
        return instance;
    }
}
