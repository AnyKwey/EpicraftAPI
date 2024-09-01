package eu.epicraft.com.events;

import eu.epicraft.com.itemstack.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OreListener implements Listener {

    @EventHandler
    public void onOre(BlockBreakEvent e){
        if(e.getBlock().getBlockData().getMaterial() == Material.COAL_ORE || e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_COAL_ORE){
            e.setExpToDrop(1);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemBuilder(Material.CHARCOAL, 3).toItemStack());
        }

        if(e.getBlock().getBlockData().getMaterial() == Material.IRON_ORE || e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_IRON_ORE){
            e.setExpToDrop(2);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemBuilder(Material.IRON_INGOT, 3).toItemStack());
        }

        if(e.getBlock().getBlockData().getMaterial() == Material.GOLD_ORE || e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_GOLD_ORE){
            e.setExpToDrop(3);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemBuilder(Material.GOLD_INGOT, 3).toItemStack());
        }

        if(e.getBlock().getBlockData().getMaterial() == Material.REDSTONE_ORE || e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_REDSTONE_ORE){
            e.setExpToDrop(4);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemBuilder(Material.REDSTONE, 3).toItemStack());
        }

        if(e.getBlock().getBlockData().getMaterial() == Material.EMERALD_ORE || e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_EMERALD_ORE){
            e.setExpToDrop(5);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemBuilder(Material.EMERALD, 3).toItemStack());
        }

        if(e.getBlock().getBlockData().getMaterial() == Material.LAPIS_ORE || e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_LAPIS_ORE){
            e.setExpToDrop(6);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemBuilder(Material.LAPIS_LAZULI, 3).toItemStack());
        }

        if(e.getBlock().getBlockData().getMaterial() == Material.DIAMOND_ORE || e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_DIAMOND_ORE){
            e.setExpToDrop(7);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemBuilder(Material.DIAMOND, 3).toItemStack());
        }
    }
}
