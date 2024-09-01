package eu.epicraft.com.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;

public class PNJMoveListener implements Listener {

    @EventHandler
    public void onPNJMove(EntityEvent e){
        Entity entity = (Villager) e.getEntity();
        entity.setInvulnerable(true);
        entity.setFreezeTicks(0);
    }
}
