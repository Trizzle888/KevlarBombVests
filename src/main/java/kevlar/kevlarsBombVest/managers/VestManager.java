package kevlar.kevlarsBombVest.managers;

import kevlar.kevlarsBombVest.KevlarsBombVest;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class VestManager {
    private final ItemStack detonator;
    private final ItemStack iedVest;
    private final ItemStack timebombVest;
    private final ItemStack clusterbombVest;

    public VestManager(KevlarsBombVest plugin) {
        this.detonator = createDetonator();
        this.iedVest = createIEDVest();
        this.timebombVest = createTimebombVest();
        this.clusterbombVest = createClusterbombVest();
    }

    private ItemStack createDetonator() {
        ItemStack item = new ItemStack(Material.STONE_BUTTON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Detonator");
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createIEDVest() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "IED Bomb Vest");
        meta.setColor(Color.BLACK);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createTimebombVest() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Timebomb Vest");
        meta.setColor(Color.GREEN);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createClusterbombVest() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Cluster Bomb Vest");
        meta.setColor(Color.BLUE);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDetonator() {
        return detonator.clone();
    }

    public ItemStack getIEDVest() {
        return iedVest.clone();
    }

    public ItemStack getTimebombVest() {
        return timebombVest.clone();
    }

    public ItemStack getClusterbombVest() {
        return clusterbombVest.clone();
    }
} 