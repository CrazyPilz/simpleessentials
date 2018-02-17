package me.alex.simpleessentials.listener;

import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener extends CommandUtils implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String quitMsg = getCfg().getString("Plugin-Quit-Msg").replace("&", "§").replace("%player%", p.getName());
        if (!isVanished(p)) {
            e.setQuitMessage(quitMsg);
        } else e.setQuitMessage(null);
    }
}
