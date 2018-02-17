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

public class FlyCommand extends CommandUtils implements CommandExecutor {

    private void save() {
        Config.save("flyPlayers");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final String prefix = getPluginPrefix();
        final String usage = prefix + " " + getPluginUsage();
        final String noPerms = prefix + " " + getPluginNoPerms();
        final String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        final String flySelfOn = prefix + " §f[§aEnabled§f] §fFly";
        final String flySelfOff = prefix + " §f[§cDisabled§f] §fFly";
        final String flyOtherOn = prefix + " §f[§aEnabled§f] §fFly for ";
        final String flyOtherOff = prefix + " §f[§cDisabled§f] §fFly for ";
        String autoFlyEnable = prefix + " §f[§aEnabled§f] §fAuto Fly";
        String autoFlyDisable = prefix + " §f[§cDisabled§f] §fAuto Fly";
        String fP = "flyPlayers";
        final String perms = "SimpleEssentials.fly.";
        final String permsall = getPermsAll();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsall)) {
                if (args.length == 0) {
                    if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsall)) {
                        if (!p.getAllowFlight()) {
                            p.setAllowFlight(true);
                            p.setFlying(true);
                            psend(p, flySelfOn);
                        } else {
                            p.setAllowFlight(false);
                            p.setFlying(false);
                            psend(p, flySelfOff);
                        }
                    } else psend(p, noPerms);
                } else if (args.length == 1) {
                    if (args(args[0], "auto")) {
                        if (!Config.getFile(fP).exists()) {
                            Config.create("data folder", fP);
                            Config.getConfig(fP).createSection("Fly");
                            save();
                        }
                        List<String> list = Config.getConfig(fP).getStringList("Fly");
                        Config.getConfig(fP).createSection("Fly");
                        save();
                        if (!list.contains(p.getUniqueId().toString())) {
                            list.add(p.getUniqueId().toString());
                            Config.getConfig(fP).set("Fly", list);
                            save();
                            psend(p, autoFlyEnable);
                        } else {
                            list.remove(p.getUniqueId().toString());
                            Config.getConfig(fP).set("Fly", list);
                            save();
                            psend(p, autoFlyDisable);
                        }
                    } else {
                        Player tplayer = Bukkit.getPlayerExact(args[0]);
                        if (tplayer != null) {
                            if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsall)) {
                                if (!tplayer.getAllowFlight()) {
                                    tplayer.setAllowFlight(true);
                                    tplayer.setFlying(true);
                                    psend(p, flyOtherOn + tplayer.getName());
                                    psend(tplayer, flySelfOn);
                                } else {
                                    tplayer.setAllowFlight(false);
                                    tplayer.setFlying(false);
                                    psend(p, flyOtherOff + tplayer.getName());
                                    psend(tplayer, flySelfOff);
                                }
                            } else psend(p, noPerms);
                        } else psend(p, noPlayerFound);
                    }
                } else psend(p, usage + " /fly [Auto] [Player]");
            } else psend(p, noPerms);
        }
        return false;
    }
}
