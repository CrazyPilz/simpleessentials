package me.alex.simpleessentials.command;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamemodeCommand extends CommandUtils implements CommandExecutor, TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Gets all the necessary Strings from Config
        final String prefix = getPluginPrefix();
        final String noPerms = prefix + " " + getPluginNoPerms();
        final String usage = prefix + " " + getPluginUsage();
        final String noPlayerFound = prefix + " " + getPluginNoPlayerFound();
        final String gmself = prefix + " " + "§aGamemode changed to §f";
        final String gmother = prefix + " " + "§aYou changed the gamemode of §f";
        final String permss = getPermsAll();
        final String permsall = "SimpleEssentials.gamemode.";
        final String perms = "SimpleEssentials.gamemode.survival.";
        final String permc = "SimpleEssentials.gamemode.creative.";
        final String perma = "SimpleEssentials.gamemode.adventure.";
        final String permsp = "SimpleEssentials.gamemode.spectator.";

        //Checks if commandSender is a player
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Checks if the player has any of the permissions
            if (perm(p, perms + "self") || perm(p, perms + "other") || perm(p, perms + "*")
                    || perm(p, permc + "self") | perm(p, permc + "other") || perm(p, permc + "*")
                    || perm(p, perma + "self") || perm(p, perma + "other") || perm(p, perma + '*')
                    || perm(p, permsp + "self") || perm(p, permsp + "other") || perm(p, permsp + "*")
                    || perm(p, permsall) || perm(p, permss)) {
                //Checks if the args length is one
                if (args.length == 1) {
                    //Checks permissions
                    if (perm(p, perms + "self") || perm(p, permc + "self") || perm(p, perma + "self") || perm(p, permsp + "self") ||
                            perm(p, perms + "*") || perm(p, permc + "*") || perm(p, perma + "*") || perm(p, permsp + "*") || perm(p, permsall) || perm(p, permss)) {
                        //Checks if the first argument is correct
                        if (args(args[0], "survival") || args(args[0], "0") || args(args[0], "s")) {
                            //Checks permissions
                            if (perm(p, perms + "self") || perm(p, perms + "*") || perm(p, permsall) || perm(p, permss)) {
                                //Sets gamemode and messages player
                                p.setGameMode(GameMode.SURVIVAL);
                                psend(p, gmself + GameMode.SURVIVAL.name());
                                //Else send noPerms Msg
                            } else psend(p, noPerms);
                            //Checks if the first argument is correct
                        } else if (args(args[0], "creative") || args(args[0], "1") || args(args[0], "c")) {
                            //Checks permissions
                            if (perm(p, permc + "self") || perm(p, permc + "*") || perm(p, permsall) || perm(p, permss)) {
                                //Sets gamemode and messages player
                                p.setGameMode(GameMode.CREATIVE);
                                psend(p, gmself + GameMode.CREATIVE.name());
                                //Else send noPerms Msg
                            } else psend(p, noPerms);
                            //Checks if the first argument is correct
                        } else if (args(args[0], "adventure") || args(args[0], "2") || args(args[0], "a")) {
                            //Checks permissions
                            if (perm(p, perma + "self") || perm(p, perma + "*") || perm(p, permsall) || perm(p, permss)) {
                                //Sets gamemode and messages player
                                p.setGameMode(GameMode.ADVENTURE);
                                psend(p, gmself + GameMode.ADVENTURE.name());
                                //Else send noPerms Msg
                            } else psend(p, noPerms);
                            //Checks if the first argument is correct
                        } else if (args(args[0], "spectator") || args(args[0], "3") || args(args[0], "sp")) {
                            //Checks permissions
                            if (perm(p, permsp + "self") || perm(p, permsp + "*") || perm(p, permsall) || perm(p, permss)) {
                                //Sets gamemode and messages player
                                p.setGameMode(GameMode.SPECTATOR);
                                psend(p, gmself + GameMode.SPECTATOR.name());
                                //Else send noPerms Msg
                            } else psend(p, noPerms);
                            //Else send Usage
                        } else psend(p, usage + " /gm [0,1,2,3]");
                        //Else send noPerms Msg
                    } else psend(p, noPerms);
                    //Checks if the args length is two
                } else if (args.length == 2) {
                    //Gets the player from the second argument
                    Player tplayer = Bukkit.getPlayer(args[1]);
                    //Checks permissions
                    if (perm(p, perms + "other") || perm(p, permc + "other") || perm(p, perma + "other") || perm(p, permsp + "other") ||
                            perm(p, perms + "*") || perm(p, permc + "*") || perm(p, perma + "*") || perm(p, permsp + "*") || perm(p, permsall) || perm(p, permss)) {
                        //Checks if the given players is online
                        if (tplayer != null) {
                            //Checks if the first argument is correct
                            if (args(args[0], "survival") || args(args[0], "0") || args(args[0], "s")) {
                                //Checks permissions
                                if (perm(p, perms + "other") || perm(p, perms + "*") || perm(p, permsall) || perm(p, permss)) {
                                    //Sets gamemode and messages both players
                                    tplayer.setGameMode(GameMode.SURVIVAL);
                                    psend(tplayer, gmself + GameMode.SURVIVAL.name());
                                    psend(p, gmother + tplayer.getName() + " §ato §f" + GameMode.SURVIVAL.name());
                                    //Else send NoPerms Msg
                                } else psend(p, noPerms);
                                //Checks if the first argument is correct
                            } else if (args(args[0], "creative") || args(args[0], "1") || args(args[0], "c")) {
                                //Checks permissions
                                if (perm(p, permc + "other") || perm(p, permc + "*") || perm(p, permsall) || perm(p, permss)) {
                                    //Sets gamemode and messages both players
                                    tplayer.setGameMode(GameMode.CREATIVE);
                                    psend(tplayer, gmself + GameMode.CREATIVE.name());
                                    psend(p, gmother + tplayer.getName() + " §ato §f" + GameMode.CREATIVE.name());
                                    //Else send NoPerms Msg
                                } else psend(p, noPerms);
                                //Checks if the first argument is correct
                            } else if (args(args[0], "adventure") || args(args[0], "2") || args(args[0], "a")) {
                                //Checks permissions
                                if (perm(p, perma + "other") || perm(p, perma + "*") || perm(p, permsall) || perm(p, permss)) {
                                    //Sets gamemode and messages both players
                                    tplayer.setGameMode(GameMode.ADVENTURE);
                                    psend(tplayer, gmself + GameMode.ADVENTURE.name());
                                    psend(p, gmother + tplayer.getName() + " §ato §f" + GameMode.ADVENTURE.name());
                                    //Else send NoPerms Msg
                                } else psend(p, noPerms);
                                //Checks if the first argument is correct
                            } else if (args(args[0], "spectator") || args(args[0], "3") || args(args[0], "sp")) {
                                //Checks permissions
                                if (perm(p, permsp + "other") || perm(p, permsp + "*") || perm(p, permsall) || perm(p, permss)) {
                                    //Sets gamemode and messages both players
                                    tplayer.setGameMode(GameMode.SPECTATOR);
                                    psend(tplayer, gmself + GameMode.SPECTATOR.name());
                                    psend(p, gmother + tplayer.getName() + " §ato §f" + GameMode.SPECTATOR.name());
                                    //Else send NoPerms Msg
                                } else psend(p, noPerms);
                                //Else send Usage
                            } else psend(p, usage + " /gm [0,1,2,3] [Player]");
                            //Else send Player not found
                        } else psend(p, noPlayerFound);
                        //Else send NoPerms Msg
                    } else psend(p, noPerms);
                    //Else send Usage
                } else psend(p, usage + " /gm [0,1,2,3] [Player]");
                //Else send NoPerms Msg
            } else psend(p, noPerms);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        String[] subcmds = {"survival", "creative", "adventure", "spectator"};
        if ((args.length > 2) || (args.length == 0)) {
            return Arrays.asList(subcmds);
        }
        if (args.length == 1) {
            List<String> matches = new ArrayList<>();
            for (String subcmd : subcmds) {
                if (subcmd.startsWith(args[0])) {
                    matches.add(subcmd);
                }
            }
            return matches;
        }
        return null;
    }
}
