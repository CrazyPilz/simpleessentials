package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String permsAll = getPermsAll();
        String perms = "SimpleEssentials.ping.";
        String pingSelf = prefix + " §aYour ping is: §f%s";
        String pingOther = prefix + " §aPing of §f%s §ais §f%s";

        if (sender instanceof Player) {
            Player p = ((Player) sender);
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        String ping = String.valueOf(((CraftPlayer) p).getHandle().ping);
                        psend(p, String.format(pingSelf, ping));
                    } else psend(p, noPerms);
                } else if (args.length ==  1) {
                    Player tPlayer = Bukkit.getPlayerExact(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (tPlayer != null) {
                            String ping = String.valueOf(((CraftPlayer)p).getHandle().ping);
                            psend(tPlayer, String.format(pingOther, p.getName(), ping));
                        } else psend(p, noPlayerFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /ping [Player]");
            } else psend(p, noPerms);
        } else noPlayer();
        return false;
    }
}
