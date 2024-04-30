package me.lagggpixel.replay;

import me.lagggpixel.replay.commands.RecordingCommand;
import me.lagggpixel.replay.listeners.BlockPlaceListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

  private static Main instance;

  @Override
  public void onEnable() {
    instance = this;

    getCommand("recording").setExecutor(new RecordingCommand());
    Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
  }

  @Override
  public void onDisable() {
  }

  public static Main getInstance() {
    return instance;
  }
}
