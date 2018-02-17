package me.alex.simpleessentials.listener;

import me.alex.simpleessentials.main.Config;
import me.alex.simpleessentials.main.Main;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.UUID;

public class VanishListener extends CommandUtils implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        for (String s : Config.getConfig("vanishedPlayers").getStringList("Vanish")) {
            UUID uuid = UUID.fromString(s);
            Player vPlayer = Bukkit.getPlayer(uuid);
            if (vPlayer != null) {
                p.hidePlayer(Main.getPlugin(), vPlayer);
            }
        }
    }

    //Unötiger Code im neusten Update aber egal
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        for (String s : Config.getConfig("vanishedPlayers").getStringList("Vanish")) {
            UUID uuid = UUID.fromString(s);
            Player vPlayer = Bukkit.getPlayer(uuid);
            if (vPlayer != null) {
                p.hidePlayer(Main.getPlugin(), vPlayer);
            }
        }
    }

    @EventHandler
    public void onPlayerVanishJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        List<String> list1 = Config.getConfig("vanishedPlayers").getStringList("Vanish");
        if (list1.contains(p.getUniqueId().toString())) {
            vanish(p);
            psend(p, getPluginPrefix() + " §f[§aEnabled§f] §fVanish");
        }
    }
}
