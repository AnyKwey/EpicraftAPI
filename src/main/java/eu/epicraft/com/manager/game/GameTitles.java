package eu.epicraft.com.manager.game;

import eu.epicraft.com.EpicraftAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTitles {

    public static void start(Player player, String gameName, String mapName, int timer){
        if(timer == 10){
            player.sendTitle("§6" + gameName, "§e" + mapName + " §7par §cEpicraft");
        }

        if(timer == 5) {
            player.sendTitle("§c5", "§eSoyez prêt !");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 5f, 5f);
        }

        if(timer == 4) {
            player.sendTitle("§c4", "§eSoyez prêt !");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 5f, 5f);
        }

        if(timer == 3) {
            player.sendTitle("§c3", "§eSoyez prêt !");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 5f, 5f);
        }

        if(timer == 2) {
            player.sendTitle("§c2", "§eSoyez prêt !");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 5f, 5f);
        }

        if(timer == 1) {
            player.sendTitle("§c1", "§eSoyez prêt !");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 5f, 5f);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 5f, 5f);
        }
    }

    public static void respawn(Player player) {
        new BukkitRunnable() {

            int timer = 3;
            @Override
            public void run() {
                timer--;

                if(timer == 3) {
                    player.sendTitle("", "§b3");
                }

                if(timer == 2) {
                    player.sendTitle("", "§b2");
                }

                if(timer == 1) {
                    player.sendTitle("", "§b1");
                }

            }
        }.runTaskTimer(EpicraftAPI.getInstance(), 0, 20);
    }

    public static void statement(Player player, boolean winnerPlayer, String winnerNameOrTeam) {
        new BukkitRunnable() {

            @Override
            public void run() {

                if (winnerPlayer) {
                    player.sendTitle("§6§lVICTOIRE", "§6Vous avez gagné !");
                } else if (!winnerPlayer) {
                    player.sendTitle("", winnerNameOrTeam + " §ea gagné");
                }
            }
        }.runTaskTimer(EpicraftAPI.getInstance(), 0, 60);
    }

    public static void death(Player player, boolean resurrected, int X, int Y, int Z, int Yaw, int Pitch, String gameName) {
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(new Location(Bukkit.getWorld("world"), X, Y, Z, Yaw, Pitch));

        if (resurrected) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(EpicraftAPI.getInstance(), new Runnable() {
                int timer = 5;

                @Override
                public void run() {
                    timer--;

                    if ((timer == 5) || (timer == 4) || (timer == 3) || (timer == 2) || (timer == 1)) {
                        player.sendTitle("§cVOUS ÊTES MORT", "§eRéaparition dans §c" + timer + " " + getSecond(timer));
                    }
                }
            }, 0, 20);
        }
    }

    public static void deathDefinitely(Player player) {
        new BukkitRunnable() {

            @Override
            public void run() {
                player.sendTitle("§cVOUS ÊTES MORT", "§eVous êtes en mode spectateur");
            }
        }.runTaskTimer(EpicraftAPI.getInstance(), 0, 30L);
    }

    private static String getSecond(int time) {
        if (time == 1) return "second";
        return "seconds";
    }
}
