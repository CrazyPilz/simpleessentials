package me.alex.simpleessentials.main;

import org.bukkit.configuration.file.FileConfiguration;


public abstract class Strings {

    public String getPluginVersion() {
        return pluginVersion;
    }

    public String getPermsAll() {
        return permsAll;
    }

    public String getPluginPrefix() {
        return pluginPrefix;
    }

    public String getPluginNoPerms() {
        return pluginNoPerms;
    }

    public String getPluginUsage() {
        return pluginUsage;
    }

    public String getPluginCmdDebugPrefix() {
        return pluginCmdDebugPrefix;
    }

    public String getPluginNoPlayerFound() {
        return pluginNoPlayerFound;
    }

    public String getPluginNoWorldFound() {
        return pluginNoWorldFound;
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    public boolean isPluginCmdDebug() {
        return pluginCmdDebug;
    }

    public boolean isPluginDayBc() {
        return pluginDayBc;
    }

    public boolean isPluginNightBc() {
        return pluginNightBc;
    }

    public boolean isPluginSunBc() {
        return pluginSunBc;
    }

    public boolean isPluginRainBc() {
        return pluginRainBc;
    }

    public boolean isPluginStormBc() {
        return pluginStormBc;
    }

    public boolean isPluginHealSilent() {
        return pluginHealSilent;
    }

    public boolean isPluginOpenInvSilent() {
        return pluginOpenInvSilent;
    }

    public boolean isPluginClearInvSilent() {
        return pluginClearInvSilent;
    }

    public boolean isPluginWorkbenchSilent() {
        return pluginWorkbenchSilent;
    }

    public boolean isPluginEnderchestOpenSilent() {
        return pluginEnderchestOpenSilent;
    }

    public boolean isPluginEnderchestClearSilent() {
        return pluginEnderchestClearSilent;
    }

    public boolean isPluginEnderchestForceOpenSilent() {
        return pluginEnderchestForceOpenSilent;
    }

    public boolean isPluginFeedSilent() {
        return pluginFeedSilent;
    }

    public boolean isPluginVanishSilent() {
        return pluginVanishSilent;
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

    private String pluginVersion = "1.0";
    public  String permsAll = "SimpleEssentials.*";
    private FileConfiguration cfg = Main.getPlugin().getConfig();
    private String pluginPrefix = cfg.getString("Plugin-Prefix").replace("&", "§");
    private String pluginNoPerms = cfg.getString("Plugin-No-Permissions").replace("&", "§");
    private String pluginUsage = cfg.getString("Plugin-Usage").replace("&", "§");
    private String pluginCmdDebugPrefix = cfg.getString("Plugin-Cmd-Debug-Prefix").replace("&", "§");
    private String pluginNoPlayerFound = cfg.getString("Plugin-Player-Offline").replace("&", "§");
    private String pluginNoWorldFound = cfg.getString("Plugin-World-Not-Found").replace("&", "§");
    private boolean pluginEnabled = cfg.getBoolean("Enable-Plugin");
    private boolean pluginCmdDebug = cfg.getBoolean("Plugin-Cmd-Debug");
    private boolean pluginDayBc = cfg.getBoolean("Plugin-Day-Bc");
    private boolean pluginNightBc= cfg.getBoolean("Plugin-Night-Bc");
    private boolean pluginSunBc = cfg.getBoolean("Plugin-Sun-Bc");
    private boolean pluginRainBc = cfg.getBoolean("Plugin-Rain-Bc");
    private boolean pluginStormBc = cfg.getBoolean("Plugin-Storm-Bc");
    private boolean pluginHealSilent = cfg.getBoolean("Plugin-Heal-Silent");
    private boolean pluginOpenInvSilent = cfg.getBoolean("Plugin-Open-Inv-Silent");
    private boolean pluginClearInvSilent = cfg.getBoolean("Plugin-Clear-Inv-Silent");
    private boolean pluginWorkbenchSilent = cfg.getBoolean("Plugin-Workbench-Silent");
    private boolean pluginEnderchestOpenSilent = cfg.getBoolean("Plugin-Enderchest-Open-Silent");
    private boolean pluginEnderchestClearSilent = cfg.getBoolean("Plugin-Enderchest-Clear-Silent");
    private boolean pluginEnderchestForceOpenSilent = cfg.getBoolean("Plugin-Enderchest-Force-Open-Silent");
    private boolean pluginFeedSilent = cfg.getBoolean("Plugin-Feed-Silent");
    private boolean pluginVanishSilent = cfg.getBoolean("Plugin-Vanish-Other-Silent");
}
