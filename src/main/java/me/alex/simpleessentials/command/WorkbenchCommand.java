package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String perms = "SimpleEssentials.workbench.";
        String permsAll = getPermsAll();
        String wbSelf = prefix + " §aOpened workbench";
        String wbOther = prefix + " §aOpened workbench for §f";
        String wbOtherMsg = prefix + " §aYou opened the workbench thanks to §f";
        boolean wbOtherSilent = isPluginWorkbenchSilent();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        p.openWorkbench(null, true);
                        psend(p, wbSelf);
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    Player tplayer = Bukkit.getPlayerExact(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (tplayer != null) {
                            tplayer.openWorkbench(null, true);
                            psend(p, wbOther + tplayer.getName());
                            if (!wbOtherSilent) {
                                psend(tplayer, wbOtherMsg + p.getName());
                            } else psend(tplayer, wbSelf);
                        } else psend(p, noPlayerFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /workbench [Player]");
            } else psend(p, noPerms);
        }
        return false;
    }
}
