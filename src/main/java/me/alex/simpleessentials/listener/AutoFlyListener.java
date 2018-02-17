package me.alex.simpleessentials.listener;

import me.alex.simpleessentials.main.Config;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class AutoFlyListener extends CommandUtils implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String prefix = getPluginPrefix();
        String fP = "flyPlayers";
        String fly = prefix + " §f[§aEnabled§f] Fly";

        Player p = e.getPlayer();
        if (!Config.getFile(fP).exists()) {
            Config.create("data folder", fP);
            Config.getConfig(fP).createSection("Fly");
            Config.save(fP);
        }
        List<String> list = Config.getConfig(fP).getStringList("Fly");
        if (list.contains(p.getUniqueId().toString())) {
            p.setAllowFlight(true);
            p.setFlying(true);
            psend(p, fly);
        }
    }
}

