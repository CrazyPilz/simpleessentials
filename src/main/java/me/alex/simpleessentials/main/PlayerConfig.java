package me.alex.simpleessentials.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PlayerConfig {

/*    private static File file;
    private static FileConfiguration cfg;
    private static File folder = new File(Main.getPlugin().getDataFolder(), "plugin data" + File.separator);

    public static void create(String s) {


        try {
            if (!Main.getPlugin().getDataFolder().exists()) {
                Main.getPlugin().getDataFolder().mkdirs();
            }

            if (!folder.exists()) {
                folder.mkdirs();
                Bukkit.getConsoleSender().sendMessage("[SE] Created " + folder.getName());
            }

            file = new File(folder, s + ".yml");
            cfg = YamlConfiguration.loadConfiguration(file);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                cfg.save(file);
                Bukkit.getConsoleSender().sendMessage("[SE] Created " + file.getName());
            } else Bukkit.getConsoleSender().sendMessage("[SE] Loaded " + file.getName());

        } catch (Exception e) {
            e.printStackTrace();

            cfg = new YamlConfiguration();
            try {
                cfg.load(file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void save() {
        try {
            cfg.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static FileConfiguration getConfig() {
        return cfg;
    }

    public static File getFile() {
        return file;
    }

    public static void load() {
        YamlConfiguration.loadConfiguration(file);
    }

/*    private static File cfile;
    private static FileConfiguration cfg;
    private static File folder = new File(Main.getPlugin().getDataFolder(), "player data" + File.separator);
    private static File mdf = Main.getPlugin().getDataFolder();

    public static void create(Player p) {
        cfile = new File(mdf, "player data" + File.separator + p.getUniqueId() + ".yml");
        if (!mdf.exists()) mdf.mkdir();
        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + cfile.getName());
            }
            cfg = YamlConfiguration.loadConfiguration(cfile);
        } else load(p);
    }
    public static File getFolder() {
        return folder;
    }
    public static File getfile() {
        return cfile;
    }
    public static void load(Player p) {
        cfile = new File(mdf, "player data" + File.separator + p.getUniqueId() + ".yml");
        cfg = YamlConfiguration.loadConfiguration(cfile);
    }
    public static FileConfiguration get() {
        return cfg;
    }
    public static void save() {
        try {
            cfg .save(cfile);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + cfile.getName());
        }
    } */
}
