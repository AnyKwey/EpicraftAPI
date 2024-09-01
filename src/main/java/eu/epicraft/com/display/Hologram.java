package eu.epicraft.com.display;

import eu.epicraft.com.EpicraftAPI;
import eu.epicraft.com.data.tools.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Hologram {

    private static final double distance = 0.24D;

    private HashMap<OfflinePlayer, Boolean> receivers;
    private HashMap<Integer, ArmorStand> entities;
    private List<String> lines;
    private Location location;
    private BukkitTask taskID;
    private double rangeView = 60;
    private boolean linesChanged = false;

    public Hologram(String... lines) {
        this.receivers = new HashMap<>();
        this.entities = new HashMap<>();

        this.lines = new ArrayList<>();
        this.lines.addAll(Arrays.asList(lines));

        this.linesChanged = true;

        this.taskID = Bukkit.getScheduler().runTaskTimerAsynchronously(EpicraftAPI.getInstance(), this::sendLinesForPlayers, 10L, 10L);
    }

    public boolean addReceiver(OfflinePlayer offlinePlayer) {
        if(!offlinePlayer.isOnline())
            return false;

        Player p = offlinePlayer.getPlayer();
        boolean inRange = false;

        if(p.getLocation().getWorld() == this.location.getWorld() && p.getLocation().distance(this.location) <= this.rangeView) {
            inRange = true;
            this.sendLines(offlinePlayer.getPlayer());
        }

        this.receivers.put(offlinePlayer, inRange);

        return true;
    }

    public boolean removeReceiver(OfflinePlayer offlinePlayer) {
        if(!offlinePlayer.isOnline())
            return false;

        this.receivers.remove(offlinePlayer);
        this.removeLines(offlinePlayer.getPlayer());

        return true;
    }

    public boolean removeLineForPlayer(Player p, int line) {
        ArmorStand entity = this.entities.get(line);

        if(entity == null)
            return false;

        entity.remove();

        return true;
    }

    public void removeLinesForPlayers() {
        for (OfflinePlayer offlinePlayer : this.receivers.keySet()) {
            if (!offlinePlayer.isOnline())
                continue;

            this.removeLines(offlinePlayer.getPlayer());
        }
    }

    public void destroy() {
        this.removeLinesForPlayers();

        this.clearEntities();
        this.clearLines();

        this.location = null;
    }

    public void fullDestroy() {
        this.destroy();
        this.receivers.clear();
        this.taskID.cancel();
    }

    public void change(String... lines) {
        this.removeLinesForPlayers();

        this.clearEntities();
        this.clearLines();

        this.lines = new ArrayList<>();
        this.lines.addAll(Arrays.asList(lines));
        this.linesChanged = true;

        this.generateLines(this.location);
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public void generateLines()
    {
        this.generateLines(this.location);
    }

    public void generateLines(Location loc) {
        Location first = loc.clone().add(0, (this.lines.size() / 2) * distance, 0);

        for (int i = 0; i < this.lines.size(); i++) {
            this.entities.put(i, generateEntitiesForLine(first.clone(), this.lines.get(i)));
            first.subtract(0, distance, 0);
        }

        this.location = loc;
    }

    public void sendLinesForPlayers() {
        for(OfflinePlayer offlinePlayer : this.receivers.keySet()) {
            if(!offlinePlayer.isOnline())
                continue;

            Player p = offlinePlayer.getPlayer();
            boolean wasInRange = this.receivers.get(offlinePlayer);
            boolean inRange = false;

            if(p.getLocation().getWorld() == this.location.getWorld() && p.getLocation().distance(this.location) <= this.rangeView)
                inRange = true;

            if(this.linesChanged && inRange) {
                this.sendLines(p);
                this.linesChanged = false;
            } else if(wasInRange == inRange) {
                continue;
            } else if(wasInRange) {
                this.removeLines(p);
            } else {
                this.sendLines(p);
            }

            this.receivers.put(offlinePlayer, inRange);
        }
    }

    public void sendLines(Player p) {
        for (int i = 0; i < this.lines.size(); i++)
            this.sendPacketForLine(p, i);
    }

    public void removeLines(Player p) {
        for (int i = 0; i < this.lines.size(); i++)
            this.removeLineForPlayer(p, i);
    }

    public void clearEntities()
    {
        this.entities.clear();
    }

    public void clearLines()
    {
        this.lines.clear();
    }

    public Location getLocation()
    {
        return this.location;
    }

    private static ArmorStand generateEntitiesForLine(Location loc, String text) {
        ArmorStand entity = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        entity.setSmall(true);
        entity.setVisible(false);
        entity.setGravity(false);
        entity.setCustomName(text);
        entity.setCustomNameVisible(true);
        loc.getWorld().spawnEntity(new Location(loc.getWorld(), loc.getX(), loc.getY() - 2, loc.getZ()), entity.getType());


        return entity;
    }

    private boolean sendPacketForLine(Player p, int line) {
        ArmorStand entity = this.entities.get(line);

        if(entity == null)
            return false;

        entity.remove();

        return true;
    }
}