package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String perms = "SimpleEssentials.feed.";
        String permsAll = getPermsAll();
        String feedSelf = prefix + " §aFed yourself";
        String feedOther = prefix + " §aFed §f";
        String feedMsg = prefix + " §aYou have been fed";
        boolean feedSilent = isPluginFeedSilent();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        p.setFoodLevel(20);
                        psend(p, feedSelf);
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    Player tplayer = Bukkit.getPlayerExact(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (tplayer != null) {
                            tplayer.setFoodLevel(20);
                            psend(p, feedOther + tplayer.getName());
                            if (!feedSilent) {
                                psend(tplayer, feedMsg + " §aby §f" + p.getName());
                            } else psend(tplayer, feedMsg);
                        } else psend(p, noPlayerFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /feed [Player]");
            } else psend(p, noPerms);
        }
        return false;
    }
}

