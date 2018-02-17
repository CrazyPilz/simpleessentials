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

public class DayCommand extends CommandUtils implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Gets necessary strings
        final String prefix = getPluginPrefix();
        final String noPerms = prefix + " " + getPluginNoPerms();
        final String usage = prefix + " " + getPluginUsage();
        final String noWorldFound = prefix + " " + getPluginNoWorldFound();
        final String dayother = prefix + " §aSwitched time to day in world §f";
        final String dayself = prefix + " §aSwitched time to day";
        final String perms = "SimpleEssentials.day.";
        final String permsall = getPermsAll();
        final boolean daybc = isPluginDayBc();

        if (sender instanceof Player) {
            //Gets player
            Player p = (Player) sender;
            //Checks perms
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms+ "*") || perm(p, permsall)) {
                //Checks args length
                if (args.length == 0) {
                    //Checks perms
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsall)) {
                        //Sets the time and send msg to player
                        p.getWorld().setTime(1L);
                        psend(p, dayself);
                        //Else sends noPerms msg
                    } else psend(p, noPerms);
                    //Checks args length
                } else if (args.length == 1) {
                    //Gets world from args
                    World w = Bukkit.getWorld(args[0]);
                    //Check perms
                    if (perm(p, perms + "other") || perm(p, perms+ "*") || perm(p, permsall)) {
                        //Checks if world exists
                        if (w != null) {
                            //Sets time in the world and sends msg
                            w.setTime(1L);
                            psend(p, dayother + w.getName());
                            //Checks if bc is enabled
                            if (daybc) {
                                //Sends all players in the world a msg
                                for (Player player : w.getPlayers()) {
                                    psend(player,prefix + " §aThe time in your world was switched to day by §f" + p.getName());
                                }
                            }
                            //Else sends noWorldFound msg
                        } else psend(p, noWorldFound);
                        //Else sends noPerms msg
                    } else psend(p, noPerms);
                    //Else sends usage msg
                } else psend(p,usage + " /day [World]");
                //Else send noPerms msg
            } else psend(p, noPerms);
        }
        return false;
    }
}
