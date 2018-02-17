package me.alex.simpleessentials.command;

import me.alex.simpleessentials.main.Main;
import me.alex.simpleessentials.main.Strings;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand extends CommandUtils implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String perms = "SimpleEssentials.enderchest.";
        String permsAll = getPermsAll();
        String ecSelf = prefix + " §aOpened enderchest";
        String ecOther = prefix + " §aOpened the enderchest from §f";
        String ecClear = prefix + " §aYou cleared the enderchest of §f";
        String ecClearMsg = prefix + " §aYour enderchest was cleared by §f";
        String ecMsg = prefix + " §aYou opened your enderchest thanks to §f";
        String ecOtherMsg = " §aopened your enderchest";
        boolean ecForceOpenSilent = isPluginEnderchestForceOpenSilent();
        boolean ecClearSilent = isPluginEnderchestClearSilent();
        boolean ecOtherSilent = isPluginEnderchestOpenSilent();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        p.openInventory(p.getEnderChest());
                        psend(p, ecSelf);
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    Player tplayer = Bukkit.getPlayerExact(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (tplayer != null) {
                            p.openInventory(tplayer.getEnderChest());
                            psend(p, ecOther + tplayer.getName());
                            if (!ecOtherSilent) {
                                psend(tplayer, prefix + " §f" + p.getName() + ecOtherMsg);
                            }
                        } else psend(p, noPlayerFound);
                    } else psend(p, noPerms);
                } else if (args.length == 2) {
                    Player tplayer = Bukkit.getPlayerExact(args[1]);
                    if (args(args[0], "clear")) {
                        if (perm(p, perms + "clear") || perm(p, perms + "*") || perm(p, permsAll)) {
                            if (tplayer != null) {
                                tplayer.getEnderChest().clear();
                                psend(p, ecClear + tplayer.getName());
                                if (!ecClearSilent) {
                                    psend(tplayer, ecClearMsg + p.getName());
                                }
                            } else psend(p, noPlayerFound);
                        } else psend(p, noPerms);
                    } else if (args(args[0], "open")) {
                        if (perm(p, perms + "open") || perm(p, perms + "*") || perm(p, permsAll)) {
                            if (tplayer != null) {
                                tplayer.openInventory(tplayer.getEnderChest());
                                psend(p, prefix + " §aYou forced §f" + tplayer.getName() + " §ato open their enderchest");
                                if (!ecForceOpenSilent) {
                                    psend(tplayer, ecMsg + p.getName());
                                } else psend(tplayer, ecSelf);
                            } else psend(p, noPlayerFound);
                        } else psend(p, noPerms);
                    } else perm(p, usage + " /enderchest [open/clear] [Player]");
                } else psend(p, usage + " /enderchest [open/clear] [Player]");
            } else psend(p, noPerms);
        }

        return false;
    }
}
