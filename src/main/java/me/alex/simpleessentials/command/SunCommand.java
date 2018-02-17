package me.alex.simpleessentials.command;

import me.alex.simpleessentials.main.Main;
import me.alex.simpleessentials.main.Strings;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SunCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noWorldFound = prefix + " " + getPluginNoWorldFound();
        String perms = "SimpleEssentials.sun.";
        String permsAll = getPermsAll();
        String sunSelf = prefix + " " + "§aSwitched weather to sun";
        String sunOther = prefix + " " + "§aSwitched weather to sun in world §f";
        boolean sunBc = isPluginSunBc();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        p.getWorld().setThundering(false);
                        p.getWorld().setStorm(false);
                        psend(p, sunSelf);
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    World w = Bukkit.getWorld(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (w != null) {
                            w.setThundering(false);
                            w.setStorm(false);
                            psend(p, sunOther + w.getName());
                            if (sunBc) {
                                for (Player player : w.getPlayers()) {
                                    psend(player, prefix + " §aThe weather in your world was switched to sun by §f" + p.getName());
                                }
                            }
                        } else psend(p, noWorldFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /sun [World]");
            } else psend(p, noPerms);
        }
        return false;
    }
}
