package me.alex.simpleessentials.listener;

import me.alex.simpleessentials.main.Main;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener extends CommandUtils implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        FileConfiguration cfg = Main.getPlugin().getConfig();
        String joinMsg = cfg.getString("Plugin-Join-Msg").replace("&", "§").replace("%player%", p.getName());
        if (!isVanished(p)) {
            e.setJoinMessage(joinMsg);
        } else e.setJoinMessage(null);
    }
}
