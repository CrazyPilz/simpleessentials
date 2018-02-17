package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Gets necessary strings from the config
        final String prefix = getPluginPrefix();
        final String version = getPluginVersion();
        final String noPerms = prefix + " " + getPluginNoPerms();
        final String usage = prefix + " " + getPluginUsage();
        //Checks if th sender is a player
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Checks Perms
            if (args.length == 0) {
                if (p.hasPermission("SimpleEssentials.info") || p.hasPermission("SimpleEssentials.*")) {
                    //Sends Messages
                    psend(p, prefix + " §2Simple Essentials");
                    psend(p, prefix + " §7Made by ChefPilz");
                    psend(p, prefix + " §7Version " + version);
                } else psend(p, noPerms);
            } else psend(p, usage + " /info");
        }
        return false;
    }
}
