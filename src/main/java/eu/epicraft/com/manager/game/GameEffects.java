package eu.epicraft.com.manager.game;

import eu.epicraft.com.manager.players.StatsManager;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameEffects {

    public static void activeDeathEffect(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 255));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 200));
    }

    public static void playLightingEffect(Location loc){
        loc.getWorld().spawnEntity(loc, EntityType.LIGHTNING);
    }

    public static void playFireworkEffect(Location loc){
        Firework f = (Firework) loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(FireworkEffect.builder()
                .flicker(false)
                .trail(false)
                .withColor(Color.fromRGB(16755200))
                .withColor(Color.fromRGB(5592575))
                .withFade(Color.fromRGB(5592575))
                .build());
        fm.setPower(0);
        f.setFireworkMeta(fm);
        f.detonate();
    }

    public static void addKill(Player player, String game){
        new StatsManager(game).setKills(player.getUniqueId(), new StatsManager(game).getKills(player.getUniqueId()) + 1);
    }

    public static void addKill(Player player, String game, boolean display, String victimName){
        new StatsManager(game).setKills(player.getUniqueId(), new StatsManager(game).getKills(player.getUniqueId()) + 1);
        if(display == true){
            player.sendTitle("§4✗ " + victimName, "", 1, 20, 1);
        }
    }

    public static void addDeath(Player player, String game){
        new StatsManager(game).setDeaths(player.getUniqueId(), new StatsManager(game).getDeaths(player.getUniqueId()) + 1);
    }
}
