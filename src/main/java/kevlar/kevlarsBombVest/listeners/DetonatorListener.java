package kevlar.kevlarsBombVest.listeners;

import kevlar.kevlarsBombVest.KevlarsBombVest;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Random;

public class DetonatorListener implements Listener {
    private final KevlarsBombVest plugin;
    private final Random random = new Random();

    public DetonatorListener(KevlarsBombVest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDetonatorClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;
        
        String itemName = item.getItemMeta().getDisplayName();
        if (!itemName.equals(ChatColor.RED + "Detonator")) return;
        
        event.setCancelled(true);
        
        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || !chestplate.hasItemMeta() || !chestplate.getItemMeta().hasDisplayName()) return;
        
        String vestName = chestplate.getItemMeta().getDisplayName();
        
        if (vestName.equals(ChatColor.DARK_GRAY + "IED Bomb Vest")) {
            detonateIED(player);
        } else if (vestName.equals(ChatColor.GREEN + "Timebomb Vest")) {
            startTimebomb(player);
        } else if (vestName.equals(ChatColor.BLUE + "Cluster Bomb Vest")) {
            detonateClusterBomb(player);
        }
    }

    private void detonateIED(Player player) {
        Location loc = player.getLocation();
        player.getWorld().createExplosion(loc, plugin.getConfigManager().getIEDRadius(), false, true);
        player.setHealth(0);
    }

    private void startTimebomb(Player player) {
        int countdown = plugin.getConfigManager().getTimebombCountdown();
        new BukkitRunnable() {
            int count = countdown;
            
            @Override
            public void run() {
                if (count > 0) {
                    player.sendTitle(ChatColor.RED + String.valueOf(count), "", 0, 20, 0);
                    count--;
                } else {
                    Location loc = player.getLocation();
                    player.getWorld().createExplosion(loc, plugin.getConfigManager().getTimebombRadius(), false, true);
                    player.setHealth(0);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private void detonateClusterBomb(Player player) {
        Location loc = player.getLocation();
        player.getWorld().createExplosion(loc, plugin.getConfigManager().getClusterbombRadius(), false, true);
        
        int range = plugin.getConfigManager().getClusterbombTNTRange();
        for (int i = 0; i < 5; i++) {
            TNTPrimed tnt = player.getWorld().spawn(loc, TNTPrimed.class);
            double x = (random.nextDouble() - 0.5) * range;
            double y = random.nextDouble() * 2;
            double z = (random.nextDouble() - 0.5) * range;
            tnt.setVelocity(loc.getDirection().add(loc.getDirection().multiply(x)).add(loc.getDirection().multiply(z)).setY(y));
            tnt.setFuseTicks(20);
        }
        
        player.setHealth(0);
    }
} 