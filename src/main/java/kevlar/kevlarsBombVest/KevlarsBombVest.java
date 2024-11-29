package kevlar.kevlarsBombVest;

import kevlar.kevlarsBombVest.commands.KBVCommand;
import kevlar.kevlarsBombVest.listeners.DetonatorListener;
import kevlar.kevlarsBombVest.managers.ConfigManager;
import kevlar.kevlarsBombVest.managers.VestManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KevlarsBombVest extends JavaPlugin {
    private static KevlarsBombVest instance;
    private ConfigManager configManager;
    private VestManager vestManager;

    @Override
    public void onEnable() {
        instance = this;
        
        // Initialize managers
        this.configManager = new ConfigManager(this);
        this.vestManager = new VestManager(this);
        
        // Register events
        getServer().getPluginManager().registerEvents(new DetonatorListener(this), this);
        
        // Register commands
        getCommand("kbv").setExecutor(new KBVCommand(this));
        getCommand("kbv").setTabCompleter(new KBVCommand(this));
        
        // Save default config
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static KevlarsBombVest getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public VestManager getVestManager() {
        return vestManager;
    }
}
