package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Gets all the necessary strings from the config etc.
        final String prefix = getPluginPrefix();
        final String noPerms = prefix + " " + getPluginNoPerms();
        final String usage = prefix + " " + getPluginUsage();
        final String noWorldFound = prefix + " " + getPluginNoWorldFound();
        final String permsall = getPermsAll();
        final String perms = "SimpleEssentials.night.";
        final String nightself = prefix + " §aSwitched time to night";
        final String nightother = prefix + " §a Switched time to night in world §f";
        final boolean nightbc = isPluginNightBc();

        if (sender instanceof Player) {
            //Gets the player
            Player p = (Player) sender;
            //Checks perms
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsall)) {
                //Checks args length
                if (args.length == 0) {
                    //Checks perms
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsall)) {
                        //Sends msg and sets to night
                        p.getWorld().setTime(13000L);
                        psend(p, nightself);
                        //Else sends noPerms msg
                    } else psend(p, noPerms);
                    //Checks args length
                } else if (args.length == 1) {
                    World w = Bukkit.getWorld(args[0]);
                    if (w != null) {
                        //Checks perms
                        if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsall)) {
                            //Sends msg and sets to night
                            w.setTime(13000L);
                            psend(p, nightother + w.getName());
                            //Checks if broadcast is enabled
                            if (nightbc) {
                                //Sends all players in the world a msg
                                for (Player player : w.getPlayers()) {
                                    psend(player, nightother + w.getName());
                                }
                            }
                            //Else sends noPerms msg
                        } else psend(p, noPerms);
                        //Else sends noWorldFound msg
                    } else psend(p, noWorldFound);
                    //Else sends usage msg
                } else psend(p, usage + " /night [World]");
                //Else sends noPerms msg
            } else psend(p, noPerms);
        }
        return false;
    }
}
