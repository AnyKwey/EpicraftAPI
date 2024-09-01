package eu.epicraft.com.manager.function;

import eu.epicraft.com.itemstack.ItemBuilder;
import eu.epicraft.com.manager.moderation.ModManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by AnyKwey
 */
public class PlayerManager {

    private static List<UUID> moderateurs = new ArrayList<>();
    private static Map<UUID, PlayerManager> players = new HashMap<>();
    private Map<UUID, Location> frozenPlayers = new HashMap<>();
    private Player player;
    private ItemStack[] items = new ItemStack[40];
    private boolean vanished;

    public PlayerManager(Player player){
        this.player = player;
        vanished = false;
    }

    public void init(){
        getPlayers().put(player.getUniqueId(), this);
        getModerateurs().add(player.getUniqueId());
        saveInventory();

        player.getInventory().setItem(0, new ItemBuilder(Material.BLAZE_ROD).setDisplayName("§cKnockback IV").addEnchantment(Enchantment.KNOCKBACK, 4).toItemStack());
        player.getInventory().setItem(1, new ItemBuilder(Material.LEAD).setDisplayName("§6Suivre").toItemStack());
        player.getInventory().setItem(3, new ItemBuilder(Material.CHEST).setDisplayName("§8Inventaire").toItemStack());
        player.getInventory().setItem(5, new ItemBuilder(Material.LIME_DYE).setDisplayName("§aInvisible").toItemStack());
        player.getInventory().setItem(7, new ItemBuilder(Material.ENDER_PEARL).setDisplayName("§2Téléportation aléatoire").toItemStack());
        player.getInventory().setItem(8, new ItemBuilder(Material.PACKED_ICE).setDisplayName("§bFreeze").toItemStack());
    }

    public void destroy(){
        getPlayers().remove(player.getUniqueId());
        getModerateurs().remove(player.getUniqueId());
        giveInventory();
    }

    public static PlayerManager getFromPlayer(Player player){
        return getPlayers().get(player.getUniqueId());
    }

    public static boolean isInModerationMod(Player player){
        return getModerateurs().contains(player.getUniqueId());
    }

    public ItemStack[] getItems() {
        return items;
    }

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(boolean vanished) {
        this.vanished = vanished;
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (vanished) {
                if(ModManager.isInMod(players.getUniqueId())) players.showPlayer(player);
                if(!ModManager.isInMod(players.getUniqueId())) players.hidePlayer(player);
            } else {
                players.showPlayer(player);
            }
        }
    }

    public void saveInventory(){
        for(int slot = 0; slot < 36; slot++){
            ItemStack item = player.getInventory().getItem(slot);
            if(item != null){
                items[slot] = item;
            }
        }

        items[36] = player.getInventory().getHelmet();
        items[37] = player.getInventory().getChestplate();
        items[38] = player.getInventory().getLeggings();
        items[39] = player.getInventory().getBoots();

        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public void giveInventory(){
        player.getInventory().clear();

        for(int slot = 0; slot < 36; slot++){
            ItemStack item = items[slot];
            if(item != null){
                player.getInventory().setItem(slot, item);
            }
        }

        player.getInventory().setHelmet(items[36]);
        player.getInventory().setChestplate(items[37]);
        player.getInventory().setLeggings(items[38]);
        player.getInventory().setBoots(items[39]);
    }

    public boolean isFreeze(Player player){
        return getFrozenPlayers().containsKey(player.getUniqueId());
    }

    public static List<UUID> getModerateurs() {
        return moderateurs;
    }

    public static Map<UUID, PlayerManager> getPlayers() {
        return players;
    }

    public Map<UUID, Location> getFrozenPlayers() {
        return frozenPlayers;
    }
}