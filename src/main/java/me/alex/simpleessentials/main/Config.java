package me.alex.simpleessentials.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    static File df = Main.getPlugin().getDataFolder();
    static File folder;
    static File file;
    static FileConfiguration cfg;

    public static void create(String fo, String fi) {
        try {
            if (!df.exists()) {
                df.mkdirs();
                Bukkit.getConsoleSender().sendMessage("[SE] Creating folder " + df.getName() + "...");
            }
            folder = new File(df, fo + File.separator);
            if (!folder.exists()) {
                folder.mkdirs();
                Bukkit.getConsoleSender().sendMessage("[SE] Creating folder " + folder.getName() + "...");
            }
            file = new File(folder, fi + ".yml");
            cfg = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                cfg.save(file);
                Bukkit.getConsoleSender().sendMessage("[SE] Creating file " + file.getName() + "...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cfg.load(file);
            Bukkit.getConsoleSender().sendMessage("[SE] Loading file " + file.getName() + "...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getFolder(String fo) {
        folder = new File(df, fo);
        return folder;
    }

    public static void save(String fi) {
        file = new File(folder, fi + ".yml");
        try {
            cfg.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getFile(String fi) {
        file = new File(folder, fi + ".yml");
        return file;
    }

    public static FileConfiguration getConfig(String fi) {
        file = new File(folder, fi + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        return cfg;
    }

    public static void load(String fo, String fi) {
        file = new File(fo, fi + " .yml");
        YamlConfiguration.loadConfiguration(file);
    }
/*    static File file;
    static File folder;
    static File df = Main.getPlugin().getDataFolder();
    static FileConfiguration cfg;

    public static void create(String s1, String s2) {
        try {
            if (!Main.getPlugin().getDataFolder().exists()) {
                Main.getPlugin().getDataFolder().mkdirs();
                Bukkit.getConsoleSender().sendMessage("[SE] Creating DataFolder" + df.getName() + "...");
            }
            folder = new File(Main.getPlugin().getDataFolder(), s1 + File.separator);
            if (!folder.exists()) {
                folder.mkdirs();
                Bukkit.getConsoleSender().sendMessage("[SE] Creating folder " + folder.getName() + " ...");
            }
            file = new File(folder + s2 + ".yml");
            cfg = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                cfg.save(file);
                Bukkit.getConsoleSender().sendMessage("[SE] Creating file " + file.getName() + " ...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cfg = new YamlConfiguration();
        try {
            cfg.load(file);
             Bukkit.getConsoleSender().sendMessage("[SE] Loading file" + file.getName() + " ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(String s) {
        try {
            cfg.save(file = new File(folder + s + ".yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig(String s) {
        return cfg = YamlConfiguration.loadConfiguration(file = new File(folder + s + ".yml"));
    }

    public static File getFolder(String s) {
        return folder = new File(df + s);
    }

    public static void load(String s) {
        YamlConfiguration.loadConfiguration(file = new File(folder + s + ".yml"));
    } */
}
