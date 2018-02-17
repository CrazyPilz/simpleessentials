package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenInventoryCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String perms = "SimpleEssentials.openinventory.";
        String permsAll = getPermsAll();
        String invOther = prefix + " §aOpened the inventory of §f";
        String invClear = prefix + " §aCleared the inventory of §f";
        String invClearedMsg = prefix + " §aYour inventory was cleared";
        boolean invOpenSilent = isPluginOpenInvSilent();
        boolean invClearSilent = isPluginClearInvSilent();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "open") || perm(p, perms + "clear") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 2) {
                    Player tplayer = Bukkit.getPlayerExact(args[1]);
                    if (args(args[0], "open")) {
                        if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                            if (tplayer != null) {
                                p.openInventory(tplayer.getInventory());
                                psend(p, invOther + tplayer.getName());
                                if (!invOpenSilent) {
                                    psend(tplayer, prefix + " §aThe player §f" + p.getName() + " §aopened your inventory");
                                }
                            } else psend(p, noPlayerFound);
                        } else psend(p, noPerms);
                    } else if (args(args[0], "clear")) {
                        if (perm(p, perms + "clear") || perm(p, perms + "*") || perm(p, permsAll)) {
                            if (tplayer != null) {
                                tplayer.getInventory().clear();
                                psend(p, invClear + tplayer.getName());
                                if (!invClearSilent) {
                                    psend(tplayer, invClearedMsg + " §aby §f" + p.getName());
                                } else psend(p, invClearedMsg);
                            } else psend(p, noPlayerFound);
                        } else psend(p, noPerms);
                    } else psend(p, usage + " /inventory [open/clear] [Player]");
                } else psend(p, usage + " /inventory [open/clear] [Player]");
            } else psend(p, noPerms);
        }

        return false;
    }
}
