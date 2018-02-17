package me.alex.simpleessentials.command;


import me.alex.simpleessentials.main.Config;
import me.alex.simpleessentials.main.Main;
import me.alex.simpleessentials.main.Strings;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VanishCommand extends CommandUtils implements CommandExecutor {

    private void save() {
        Config.save("vanishedPlayers");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        String perms = "SimpleEssentials.vanish.";
        String permsAll = getPermsAll();
        String vOn = prefix + " §f[§aEnabled§f] §fVanish";
        String vOff = prefix + " §f[§cDisabled§f] §fVanish";
        String vOtherMsg = vOn + " §athanks to §f";
        String vP = "vanishedPlayers";
        boolean vOtherSilent = isPluginVanishSilent();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (!Config.getFile(vP).exists()) {
                    Config.create("plugin data", vP);
                    Config.getConfig(vP).createSection("Vanish");
                    save();
                }
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        List<String> list = Config.getConfig("vanishedPlayers").getStringList("Vanish");
                        if (!list.contains(p.getUniqueId().toString())) {
                            list.add(p.getUniqueId().toString());
                            Config.getConfig("vanishedPlayers").set("Vanish", list);
                            save();
                            vanish(p);
                            psend(p, vOn);
                        } else {
                            list.remove(p.getUniqueId().toString());
                            Config.getConfig("vanishedPlayers").set("Vanish", list);
                            save();
                            unVanish(p);
                            psend(p, vOff);
                        }
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    Player tPlayer = Bukkit.getPlayer(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (tPlayer != null) {
                            List<String> list = Config.getConfig("vanishedPlayers").getStringList("Vanish");
                            if (!list.contains(tPlayer.getUniqueId().toString())) {
                                list.add(tPlayer.getUniqueId().toString());
                                Config.getConfig("vanishedPlayers").set("Vanish", list);
                                save();
                                vanish(tPlayer);
                                psend(p, vOn + " §afor §f" + tPlayer.getName());
                                if (!vOtherSilent) {
                                    psend(tPlayer, vOn + vOtherMsg + p.getName());
                                } else psend(tPlayer, vOn);
                            } else {
                                list.remove(tPlayer.getUniqueId().toString());
                                Config.getConfig("vanishedPlayers").set("Vanish", list);
                                save();
                                unVanish(tPlayer);
                                psend(p, vOff + " §afor §f" + tPlayer.getName());
                                if (!vOtherSilent) {
                                    psend(tPlayer, vOff + vOtherMsg + p.getName());
                                } else psend(tPlayer, vOff);
                            }
                        } else psend(p, noPlayerFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /vanish [Player]");
            } else psend(p, noPerms);
        }
        return false;
    }
}
