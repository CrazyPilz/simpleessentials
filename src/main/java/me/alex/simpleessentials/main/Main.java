package me.alex.simpleessentials.main;

import me.alex.simpleessentials.command.*;
import me.alex.simpleessentials.listener.*;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;

        //Copies default config
        FileConfiguration cfg = Main.getPlugin().getConfig();
        cfg.options().copyDefaults(true);
        Main.getPlugin().saveConfig();

        boolean pluginEnabled = cfg.getBoolean("Enable-Plugin");

        //Check if Plugin is enabled in Config
        if (pluginEnabled) {
            //Sends Msg in Console that the Plugin was enabled
            System.out.println("[SE] Simple Essentials successfully activated!");

            //Prints the current config options out
            System.out.println("[SE] Plugin Prefix: " + cfg.getString("Plugin-Prefix"));
            System.out.println("[SE] Plugin No Perms Msg: " + cfg.getString("Plugin-No-Permissions"));
            System.out.println("[SE] Plugin Usage Msg: " + cfg.getString("Plugin-Usage"));
            System.out.println("[SE] Plugin Player Not Found Msg: " + cfg.getString("Plugin-Player-Offline"));
            System.out.println("[SE] Plugin World Not Found Msg: " + cfg.getString("Plugin-World-Not-Found"));
            System.out.println("[SE] Plugin Cmd Debug Prefix: " + cfg.getString("Plugin-Cmd-Debug-Prefix"));
            System.out.println("[SE] Plugin Enable Day Bc : " + cfg.getBoolean("Plugin-Day-Bc"));
            System.out.println("[SE] Plugin Enable Night Bc: " + cfg.getBoolean("Plugin-Night-Bc"));
            System.out.println("[SE] Plugin Enable Sun Bc: " + cfg.getBoolean("Plugin-Sun-Bc"));
            System.out.println("[SE] Plugin Enable Rain Bc: " + cfg.getBoolean("Plugin-Rain-Bc"));
            System.out.println("[SE] Plugin Enable Storm Bc: " + cfg.getBoolean("Plugin-Storm-Bc"));
            System.out.println("[SE] Plugin Heal Silent: " + cfg.getBoolean("Plugin-Heal-Silent"));
            System.out.println("[SE] Plugin Open Inventory Silent: " + cfg.getBoolean("Plugin-Open-Inv-Silent"));
            System.out.println("[SE] Plugin Clear Inventory Silent: " + cfg.getBoolean("Plugin-Clear-Inv-Silent"));
            System.out.println("[SE] Plugin Workbench Silent: " + cfg.getBoolean("Plugin-Workbench-Silent"));
            System.out.println("[SE] Plugin Enderchest Open Silent: " + cfg.getBoolean("Plugin-Enderchest-Open-Silent"));
            System.out.println("[SE] Plugin Enderchest Force Open Silent: " + cfg.getBoolean("Plugin-Enderchest-Force-Open-Silent"));
            System.out.println("[SE] Plugin Enderchest Clear Silent: " + cfg.getBoolean("Plugin-Enderchest-Clear-Silent"));
            System.out.println("[SE] Plugin Feed Silent: " + cfg.getBoolean("Plugin-Feed-Silent"));
            System.out.println("[SE] Plugin Vanish Other Silent: " + cfg.getBoolean("Plugin-Vanish-Other-Silent"));

            Config.create("plugin data", "debugPlayers");
            Config.create("plugin data", "flyPlayers");
            Config.create("plugin data", "vanishedPlayers");

            //getCommand("test").setExecutor(test);
            //Register Commands
            getCommand("info").setExecutor(new InfoCommand());
            getCommand("gm").setExecutor(new GamemodeCommand());
            getCommand("day").setExecutor(new DayCommand());
            getCommand("night").setExecutor(new NightCommand());
            getCommand("fly").setExecutor(new FlyCommand());
            getCommand("debug").setExecutor(new DebugCommand());
            getCommand("test").setExecutor(new TestCommand());
            getCommand("sun").setExecutor(new SunCommand());
            getCommand("rain").setExecutor(new RainCommand());
            getCommand("storm").setExecutor(new StormCommand());
            getCommand("uuid").setExecutor(new UUIDCommand());
            getCommand("heal").setExecutor(new HealCommand());
            getCommand("inventory").setExecutor(new OpenInventoryCommand());
            getCommand("workbench").setExecutor(new WorkbenchCommand());
            getCommand("enderchest").setExecutor(new EnderChestCommand());
            getCommand("feed").setExecutor(new FeedCommand());
            getCommand("vanish").setExecutor(new VanishCommand());

            //Register Events
            PluginManager pm = Bukkit.getPluginManager();
            pm.registerEvents(new DebugListener(), this);
            pm.registerEvents(new AutoFlyListener(), this);
            pm.registerEvents(new VanishListener(), this);
            pm.registerEvents(new JoinListener(), this);
            pm.registerEvents(new QuitListener(), this);
            //pm.registerEvent(new Test(), this);
        } else {
            //Else disables it and prints out in Console
            Main.getPlugin().getPluginLoader().disablePlugin(this);
            System.out.println("[SE] The plugin wasn't enabled! Please active it via the config");
        }
    }
}
