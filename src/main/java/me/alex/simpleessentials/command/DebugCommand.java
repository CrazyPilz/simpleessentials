package me.alex.simpleessentials.command;

import me.alex.simpleessentials.main.Config;
import me.alex.simpleessentials.main.Main;
import me.alex.simpleessentials.main.PlayerConfig;
import me.alex.simpleessentials.main.Strings;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class DebugCommand extends CommandUtils implements CommandExecutor {

    private void save() {
        String dP = "debugPlayers";
        Config.save(dP);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final String prefix = getPluginPrefix();
        final String usage = prefix + " " + getPluginUsage();
        final String noPerms = prefix + " " + getPluginNoPerms();
        final String debugOn = prefix + " §f[§aEnabled§f] §fDebug";
        final String debugOff = prefix + " §f[§cDisabled§f] §fDebug";
        final String perms = "SimpleEssentials.debug";
        final String permsall = getPermsAll();
        String dP = "debugPlayers";

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms) || perm(p, permsall)) {
                if (args.length == 0) {
                    if (!Config.getFile(dP).exists()) {
                        Config.create("plugin data", dP);
                        Config.getConfig(dP).createSection("Debug");
                        save();
                    } else Config.load("plugin data", dP);
                        List<String> list = Config.getConfig(dP).getStringList("Debug");
                        Config.getConfig(dP).createSection("Debug");
                        Config.save(dP);
                        if (!list.contains(p.getUniqueId().toString())) {
                            list.add(p.getUniqueId().toString());
                            Config.getConfig(dP).set("Debug", list);
                            save();
                            psend(p, debugOn);
                        } else {
                            list.remove(p.getUniqueId().toString());
                            Config.getConfig(dP).set("Debug", list);
                            save();
                            psend(p, debugOff);
                        }

                } else psend(p, usage + " /debug");
            } else psend(p, noPerms);
        }
        return false;
    }
}
