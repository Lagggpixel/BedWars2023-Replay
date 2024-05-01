package me.lagggpixel.replay;

import org.bukkit.plugin.java.JavaPlugin;

public final class Replay extends JavaPlugin {

    private static Replay instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Replay getInstance() {
        return instance;
    }
}
