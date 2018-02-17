package me.alex.simpleessentials.command;

import me.alex.simpleessentials.main.Config;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class DebugListener extends CommandUtils implements Listener {

    @EventHandler
    public void onCmdExecuteDebug(PlayerCommandPreprocessEvent e) {
        //Checks if plugin debug is enabled
        boolean cmdDebug = isPluginCmdDebug();
        if (cmdDebug) {
            Player p = e.getPlayer();
            String cmd = e.getMessage();
            String prefix = getPluginCmdDebugPrefix();
            //Gets all online players
            for (Player player : Bukkit.getOnlinePlayers()) {
                List<String> list = Config.getConfig("debugPlayers").getStringList("Debug");
                if (list.contains(player.getUniqueId().toString())) {
                    //Sends debug info
                    psend(player, "§f[§cDEBUG§f] §f" + prefix + " " + p.getName() + " executed " + cmd);
                }
            }
        }
    }
}
