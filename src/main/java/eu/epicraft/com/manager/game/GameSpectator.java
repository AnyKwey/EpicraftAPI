package eu.epicraft.com.manager.game;

import eu.epicraft.com.EpicraftAPI;
import eu.epicraft.com.itemstack.ItemBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameSpectator {

    private static ArrayList<Player> spectator = new ArrayList<>();

    public static void add(Player player){
        if(spectator.contains(player)) return;
        spectator.add(player);

        player.getInventory().clear();
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getInventory().setHelmet(null);
        player.getInventory().setItem(0, new ItemBuilder(Material.NETHER_STAR).setDisplayName("§eRe-jouer").toItemStack());
        player.getInventory().setItem(4, new ItemBuilder(Material.RECOVERY_COMPASS).setDisplayName("§bListe des joueurs").toItemStack());
        player.getInventory().setItem(8, new ItemBuilder(Material.RED_BED).setDisplayName("§cRetourner au lobby").toItemStack());
        player.setGameMode(GameMode.ADVENTURE);

        Bukkit.getOnlinePlayers().stream().filter(players -> !isSpectator(players)).forEach(players -> players.hidePlayer(player));

        Bukkit.getScheduler().scheduleSyncRepeatingTask(EpicraftAPI.getInstance(), new Runnable() {
            int timer = 0;

            @Override
            public void run() {
                timer++;

                if(timer == 1){
                    timer = 0;
                }

                if (timer == 0) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§5Mode Spectateur"));
                }
            }
        }, 0, 20);
    }

    public static void remove(Player player){
        if(!spectator.contains(player)) return;
        spectator.remove(player);
    }

    public static boolean isSpectator(Player player){
        return spectator.contains(player);
    }
}
