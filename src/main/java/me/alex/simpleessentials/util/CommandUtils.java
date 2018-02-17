package me.alex.simpleessentials.util;

import me.alex.simpleessentials.main.Config;
import me.alex.simpleessentials.main.Main;
import me.alex.simpleessentials.main.Strings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class CommandUtils extends Strings {

    public void psend(Player player, String content) {
        player.sendMessage(content);
    }

    public boolean perm(Player player, String permission) {
        return player.hasPermission(permission);
    }

    public boolean args(String args, String argument) {
        return args.equalsIgnoreCase(argument);
    }

    public void noPlayer() {
        Bukkit.getConsoleSender().sendMessage("[SE] You can only use this command as a player");
    }

    public boolean isVanished(Player p) {
        List<String> list = Config.getConfig("vanishedPlayers").getStringList("Vanish");
        return (list.contains(p.getUniqueId().toString()));
    }

    public void vanish(Player p) {
        List<String> list = Config.getConfig("vanishedPlayers").getStringList("Vanish");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != null) {
                player.hidePlayer(Main.getPlugin(), p);
            }
        }
        for (String s : list) {
            UUID uuid = UUID.fromString(s);
            Player vPlayer = Bukkit.getPlayer(uuid);
            if (vPlayer != null) {
                p.showPlayer(Main.getPlugin(), vPlayer);
            }
        }
    }

    public void unVanish(Player p) {
        List<String> list = Config.getConfig("vanishedPlayers").getStringList("Vanish");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != null) {
                player.showPlayer(Main.getPlugin(), p);
            }
        }
        for (String s : list) {
            UUID uuid = UUID.fromString(s);
            Player vPlayer = Bukkit.getPlayer(uuid);
            if (vPlayer != null) {
                p.hidePlayer(Main.getPlugin(), vPlayer);
            }
        }
    }

}
