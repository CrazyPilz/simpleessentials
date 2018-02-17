package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class UUIDCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String perms = "SimpleEssentials.uuid.";
        String permsAll = getPermsAll();
        String uuidSelf = prefix + " §aYour UUID is: §f";
        String uuidOther;
        String cc = prefix + " §aCopied the UUID to your clipboard!";
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection;

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        String uuid = p.getUniqueId().toString();
                        psend(p, uuidSelf + uuid);
                        selection = new StringSelection(uuid);
                        c.setContents(selection, selection);
                        psend(p, cc);
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    Player tplayer = Bukkit.getPlayerExact(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (tplayer != null) {
                            uuidOther = prefix + " §f" + tplayer.getName() + "§a's UUID is: §f";
                            String uuid = tplayer.getUniqueId().toString();
                            psend(p, uuidOther + uuid);
                            selection = new StringSelection(uuid);
                            c.setContents(selection, selection);
                            psend(p, cc);
                        } else psend(p, noPlayerFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /uuid [Player]");
            } else psend(p, noPerms);
        }
        return false;
    }
}
