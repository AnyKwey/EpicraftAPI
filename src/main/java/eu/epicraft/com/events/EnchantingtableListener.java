package eu.epicraft.com.events;

import eu.epicraft.com.itemstack.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnchantingtableListener implements Listener {

    @EventHandler
    public void onEnchantingtable(PlayerInteractEvent e){
        if(e.getClickedBlock().getType() == Material.ENCHANTING_TABLE){
            Inventory i = Bukkit.createInventory(null, 6*9, "§8Enchanter");

            int[] slots = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 17,18, 19, 21, 22, 26, 27, 28, 29, 30, 31, 35, 36, 37, 38, 39, 40,41, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54};
            Material x = i.getItem(20).getType();

            for(int glass : slots){
                i.setItem(glass, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).toItemStack());
            }

            if(x == Material.WOODEN_SWORD || x == Material.STONE_SWORD || x == Material.IRON_SWORD || x == Material.GOLDEN_SWORD || x == Material.DIAMOND_SWORD || x == Material.NETHERITE_SWORD || x == Material.WOODEN_AXE || x == Material.STONE_AXE || x == Material.IRON_AXE || x == Material.GOLDEN_AXE || x == Material.DIAMOND_AXE || x == Material.NETHERITE_AXE){
                i.setItem(14, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DAMAGE_ALL, 1).toItemStack());
                i.setItem(15, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DAMAGE_ALL, 2).toItemStack());
                i.setItem(16, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.DAMAGE_ALL, 3).toItemStack());

                i.setItem(23, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.KNOCKBACK, 1).toItemStack());
                i.setItem(24, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.KNOCKBACK, 2).toItemStack());
                i.setItem(25, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack());

                i.setItem(32, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DURABILITY, 1).toItemStack());
                i.setItem(33, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DURABILITY, 2).toItemStack());
                i.setItem(34, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.DURABILITY, 3).toItemStack());

                i.setItem(41, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(42, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(43, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
            } else if(x == Material.WOODEN_PICKAXE || x == Material.STONE_PICKAXE || x == Material.IRON_PICKAXE || x == Material.GOLDEN_PICKAXE || x == Material.DIAMOND_PICKAXE || x == Material.NETHERITE_PICKAXE || x == Material.WOODEN_SHOVEL || x == Material.STONE_SHOVEL || x == Material.IRON_SHOVEL || x == Material.GOLDEN_SHOVEL || x == Material.DIAMOND_SHOVEL || x == Material.NETHERITE_SHOVEL){
                i.setItem(14, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack());
                i.setItem(15, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DIG_SPEED, 2).toItemStack());
                i.setItem(16, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.DIG_SPEED, 3).toItemStack());

                i.setItem(23, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 4xp").addEnchantment(Enchantment.DIG_SPEED, 4).toItemStack());
                i.setItem(24, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1).toItemStack());
                i.setItem(25, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2).toItemStack());

                i.setItem(32, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DURABILITY, 1).toItemStack());
                i.setItem(33, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DURABILITY, 2).toItemStack());
                i.setItem(34, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.DURABILITY, 3).toItemStack());

                i.setItem(41, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(42, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(43, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
            } else if(x == Material.LEATHER_HELMET || x == Material.IRON_HELMET || x == Material.GOLDEN_HELMET || x == Material.DIAMOND_HELMET || x == Material.NETHERITE_HELMET){
                i.setItem(14, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
                i.setItem(15, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                i.setItem(16, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());

                i.setItem(23, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 4xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                i.setItem(24, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DURABILITY, 1).toItemStack());
                i.setItem(25, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DURABILITY, 2).toItemStack());

                i.setItem(32, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.DURABILITY, 3).toItemStack());
                i.setItem(33, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(34, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());

                i.setItem(41, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(42, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(43, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
            } else if(x == Material.LEATHER_CHESTPLATE || x == Material.IRON_CHESTPLATE || x == Material.GOLDEN_CHESTPLATE || x == Material.DIAMOND_CHESTPLATE || x == Material.NETHERITE_CHESTPLATE){
                i.setItem(14, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
                i.setItem(15, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                i.setItem(16, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());

                i.setItem(23, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 4xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                i.setItem(24, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DAMAGE_UNDEAD, 1).toItemStack());
                i.setItem(25, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DAMAGE_UNDEAD, 2).toItemStack());

                i.setItem(32, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DURABILITY, 1).toItemStack());
                i.setItem(33, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DURABILITY, 2).toItemStack());
                i.setItem(34, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.DURABILITY, 3).toItemStack());

                i.setItem(41, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(42, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(43, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
            } else if(x == Material.LEATHER_LEGGINGS || x == Material.IRON_LEGGINGS || x == Material.GOLDEN_LEGGINGS || x == Material.DIAMOND_LEGGINGS || x == Material.NETHERITE_LEGGINGS){
                i.setItem(14, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
                i.setItem(15, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                i.setItem(16, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());

                i.setItem(23, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 4xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                i.setItem(24, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DURABILITY, 1).toItemStack());
                i.setItem(25, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DURABILITY, 2).toItemStack());

                i.setItem(32, new ItemBuilder(Material.ENCHANTED_BOOK).addEnchantment(Enchantment.DURABILITY, 3).toItemStack());
                i.setItem(33, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(34, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());

                i.setItem(41, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(42, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(43, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
            } else if(x == Material.LEATHER_BOOTS || x == Material.IRON_BOOTS || x == Material.GOLDEN_BOOTS || x == Material.DIAMOND_BOOTS || x == Material.NETHERITE_BOOTS){
                i.setItem(14, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
                i.setItem(15, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                i.setItem(16, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());

                i.setItem(23, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 4xp").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                i.setItem(24, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.PROTECTION_FALL, 1).toItemStack());
                i.setItem(25, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.PROTECTION_FALL, 2).toItemStack());

                i.setItem(32, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.PROTECTION_FALL, 3).toItemStack());
                i.setItem(33, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 1xp").addEnchantment(Enchantment.DURABILITY, 1).toItemStack());
                i.setItem(34, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 2xp").addEnchantment(Enchantment.DURABILITY, 2).toItemStack());

                i.setItem(41, new ItemBuilder(Material.ENCHANTED_BOOK).setLore("§aEnchantement à 3xp").addEnchantment(Enchantment.DURABILITY, 3).toItemStack());
                i.setItem(42, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(43, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
            } else {
                i.setItem(14, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(15, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(16, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(23, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(24, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(25, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(32, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(33, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(34, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(41, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(42, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
                i.setItem(43, new ItemBuilder(Material.BARRIER).setDisplayName("§c§lIndiponisble").toItemStack());
            }
            e.getPlayer().openInventory(i);
        }
    }

    @EventHandler
    public void onInv(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        ItemStack i = e.getCurrentItem();
        Inventory inv = e.getClickedInventory();

        if(inv.equals("§8Enchanter")){
            Material x = inv.getItem(20).getType();

            if(i.getType() == Material.BLACK_STAINED_GLASS_PANE && i.getType() == Material.BARRIER){
                e.setCancelled(true);
            }

            if(i.getEnchantments() == Enchantment.DAMAGE_ALL){
                if(i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 1){
                    e.setCancelled(true);
                    if(player.getLevel() < 1){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 2){
                    e.setCancelled(true);
                    if(player.getLevel() < 2){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.DAMAGE_ALL, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 3){
                    e.setCancelled(true);
                    if(player.getLevel() < 3){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 3);
                        inv.getItem(20).addEnchantment(Enchantment.DAMAGE_ALL, 3);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            } else if(i.getEnchantments() == Enchantment.KNOCKBACK){
                if(i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 1){
                    e.setCancelled(true);
                    if(player.getLevel() < 1){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.KNOCKBACK, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 2){
                    e.setCancelled(true);
                    if(player.getLevel() < 2){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.KNOCKBACK, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            } else if(i.getEnchantments() == Enchantment.FIRE_ASPECT) {
                if (i.getEnchantmentLevel(Enchantment.FIRE_ASPECT) == 1) {
                    e.setCancelled(true);
                    if (player.getLevel() < 1) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.FIRE_ASPECT, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            } else if(i.getEnchantments() == Enchantment.DURABILITY){
                if(i.getEnchantmentLevel(Enchantment.DURABILITY) == 1){
                    e.setCancelled(true);
                    if(player.getLevel() < 1){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.DURABILITY, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.DURABILITY) == 2){
                    e.setCancelled(true);
                    if(player.getLevel() < 2){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.DURABILITY, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.DURABILITY) == 3){
                    e.setCancelled(true);
                    if(player.getLevel() < 3){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 3);
                        inv.getItem(20).addEnchantment(Enchantment.DURABILITY, 3);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            } else if(i.getEnchantments() == Enchantment.DIG_SPEED){
                if(i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 1){
                    e.setCancelled(true);
                    if(player.getLevel() < 1){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.DIG_SPEED, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 2){
                    e.setCancelled(true);
                    if(player.getLevel() < 2){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.DIG_SPEED, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 3){
                    e.setCancelled(true);
                    if(player.getLevel() < 3){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 3);
                        inv.getItem(20).addEnchantment(Enchantment.DIG_SPEED, 3);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if(i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 4){
                    e.setCancelled(true);
                    if(player.getLevel() < 4){
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 4);
                        inv.getItem(20).addEnchantment(Enchantment.DIG_SPEED, 4);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            } else if(i.getEnchantments() == Enchantment.PROTECTION_ENVIRONMENTAL) {
                if (i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 1) {
                    e.setCancelled(true);
                    if (player.getLevel() < 1) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if (i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 2) {
                    e.setCancelled(true);
                    if (player.getLevel() < 2) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if (i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 3) {
                    e.setCancelled(true);
                    if (player.getLevel() < 3) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 3);
                        inv.getItem(20).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if (i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4) {
                    e.setCancelled(true);
                    if (player.getLevel() < 4) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 4);
                        inv.getItem(20).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            } else if(i.getEnchantments() == Enchantment.LOOT_BONUS_BLOCKS) {
                if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                    e.setCancelled(true);
                    if (player.getLevel() < 1) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                    e.setCancelled(true);
                    if (player.getLevel() < 2) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            }  else if(i.getEnchantments() == Enchantment.DAMAGE_UNDEAD) {
                if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 1) {
                    e.setCancelled(true);
                    if (player.getLevel() < 1) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 2) {
                    e.setCancelled(true);
                    if (player.getLevel() < 2) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            } else if(i.getEnchantments() == Enchantment.PROTECTION_FALL) {
                if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 1) {
                    e.setCancelled(true);
                    if (player.getLevel() < 1) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 1);
                        inv.getItem(20).addEnchantment(Enchantment.PROTECTION_FALL, 1);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 2) {
                    e.setCancelled(true);
                    if (player.getLevel() < 2) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 2);
                        inv.getItem(20).addEnchantment(Enchantment.PROTECTION_FALL, 2);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                } else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 3) {
                    e.setCancelled(true);
                    if (player.getLevel() < 3) {
                        player.sendMessage("§8[§c§l✗§8] §cVous n'avez pas assez d'xp.");
                    } else {
                        player.setLevel(player.getLevel() - 3);
                        inv.getItem(20).addEnchantment(Enchantment.PROTECTION_FALL, 3);
                        player.sendMessage("§8[§a§l✔§8] §cEnchantement appliquée.");
                    }
                }
            }
        }
    }
}
