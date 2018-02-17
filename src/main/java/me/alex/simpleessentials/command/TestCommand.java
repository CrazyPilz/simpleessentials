package me.alex.simpleessentials.command;

import me.alex.simpleessentials.main.Config;
import me.alex.simpleessentials.util.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends CommandUtils implements CommandExecutor {

    private void save() {
        Config.save("test");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            try {
                if (!Config.getFile("test").exists()) {
                    Config.create("test", "test");
                    Config.getConfig("test").createSection("test1");
                    save();
                    Config.getConfig("test").set("test", "test");
                    save();
                } else Config.load("test", "test");
                p.sendMessage(Config.getFile("test").getName());
                p.sendMessage(Config.getFolder("test").getPath());
                p.sendMessage(Config.getConfig("test").getString("test"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
