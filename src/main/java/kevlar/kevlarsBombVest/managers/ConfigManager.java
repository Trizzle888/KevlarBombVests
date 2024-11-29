package kevlar.kevlarsBombVest.managers;

import kevlar.kevlarsBombVest.KevlarsBombVest;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final KevlarsBombVest plugin;
    private FileConfiguration config;

    public ConfigManager(KevlarsBombVest plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
        
        // Set defaults if config is empty
        if (config.getKeys(false).isEmpty()) {
            setDefaults();
        }
    }

    private void setDefaults() {
        config.set("vests.ied.radius", 5);
        config.set("vests.ied.damage", 10);
        
        config.set("vests.timebomb.radius", 5);
        config.set("vests.timebomb.damage", 10);
        config.set("vests.timebomb.countdown", 5);
        
        config.set("vests.clusterbomb.radius", 5);
        config.set("vests.clusterbomb.damage", 10);
        config.set("vests.clusterbomb.tnt-range", 10);
        
        plugin.saveConfig();
    }

    public int getIEDRadius() {
        return config.getInt("vests.ied.radius");
    }

    public double getIEDDamage() {
        return config.getDouble("vests.ied.damage");
    }

    public int getTimebombRadius() {
        return config.getInt("vests.timebomb.radius");
    }

    public double getTimebombDamage() {
        return config.getDouble("vests.timebomb.damage");
    }

    public int getTimebombCountdown() {
        return config.getInt("vests.timebomb.countdown");
    }

    public int getClusterbombRadius() {
        return config.getInt("vests.clusterbomb.radius");
    }

    public double getClusterbombDamage() {
        return config.getDouble("vests.clusterbomb.damage");
    }

    public int getClusterbombTNTRange() {
        return config.getInt("vests.clusterbomb.tnt-range");
    }
} 