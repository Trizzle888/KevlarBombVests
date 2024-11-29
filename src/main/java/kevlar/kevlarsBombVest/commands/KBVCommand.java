package kevlar.kevlarsBombVest.commands;

import kevlar.kevlarsBombVest.KevlarsBombVest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KBVCommand implements CommandExecutor, TabCompleter {
    private final KevlarsBombVest plugin;

    public KBVCommand(KevlarsBombVest plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /kbv <reload|give> [vest]");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("kbv.reload")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
            plugin.getConfigManager().loadConfig();
            sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission("kbv.give")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /kbv give <vest>");
                return true;
            }

            Player player = (Player) sender;
            switch (args[1].toLowerCase()) {
                case "ied":
                    player.getInventory().addItem(plugin.getVestManager().getIEDVest());
                    break;
                case "timebomb":
                    player.getInventory().addItem(plugin.getVestManager().getTimebombVest());
                    break;
                case "clusterbomb":
                    player.getInventory().addItem(plugin.getVestManager().getClusterbombVest());
                    break;
                case "detonator":
                    player.getInventory().addItem(plugin.getVestManager().getDetonator());
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "Invalid vest type!");
                    return true;
            }
            sender.sendMessage(ChatColor.GREEN + "Item given!");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Unknown command!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("reload", "give");
        }
        
        if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            return Arrays.asList("ied", "timebomb", "clusterbomb", "detonator");
        }
        
        return new ArrayList<>();
    }
} 