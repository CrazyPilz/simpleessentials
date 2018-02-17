package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String perms = "SimpleEssentials.heal.";
        String permsAll = getPermsAll();
        String healSelf = prefix + " §aHealed yourself";
        String healOther = prefix + " §aHealed §f";
        String healMsg = prefix + " §aYou have been healed";
        boolean healSilent = isPluginHealSilent();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        p.setHealth(20d);
                        p.setFoodLevel(20);
                        psend(p, healSelf);
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    Player tplayer = Bukkit.getPlayerExact(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (tplayer != null) {
                            tplayer.setHealth(20d);
                            tplayer.setFoodLevel(20);
                            psend(p, healOther + tplayer.getName());
                            if (!healSilent) {
                                psend(tplayer, healMsg + " §aby §f" + p.getName());
                            } else psend(tplayer, healMsg);
                        } else psend(p, noPlayerFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /heal [Player]");
            } else psend(p, noPerms);
        }
        return false;
    }
}
