package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StormCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = getPluginPrefix();
        String noPerms = prefix + " " + getPluginNoPerms();
        String usage = prefix + " " + getPluginUsage();
        String noWorldFound = prefix + " " + getPluginNoWorldFound();
        String stormSelf = prefix + " §aSwitched weather to storm";
        String stormOther = prefix + " §aSwitched weather to storm in world §f";
        String perms = "SimpleEssentials.storm.";
        String permsAll = getPermsAll();
        boolean stormBc = isPluginStormBc();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsAll)) {
                        p.getWorld().setStorm(true);
                        p.getWorld().setThundering(true);
                        psend(p, stormSelf);
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    World w = Bukkit.getWorld(args[0]);
                    if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsAll)) {
                        if (w != null) {
                            w.setThundering(true);
                            w.setStorm(true);
                            psend(p, stormOther + w.getName());
                            if (stormBc) {
                                for (Player player : w.getPlayers()) {
                                    psend(player, prefix + " §aThe weather in your world was switched to storm by §f" + p.getName());
                                }
                            }
                        } else psend(p, noWorldFound);
                    } else psend(p, noPerms);
                } else psend(p, usage + " /storm [World]");
            } else psend(p, noPerms);
        }
        return false;
    }
}
