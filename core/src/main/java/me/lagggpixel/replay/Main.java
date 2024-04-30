package me.lagggpixel.replay;

import me.lagggpixel.replay.commands.ReplayCommand;
import me.lagggpixel.replay.listeners.BlockPlaceListener;
import me.lagggpixel.replay.version_support.nms.NMS;
import me.lagggpixel.replay.version_support.v1_8_r3.V1_8_R3;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

  private static Main instance;
  private static NMS version;

  @Override
  public void onEnable() {
    instance = this;

    switch (Bukkit.getServer().getBukkitVersion().split("-")[0]) {
      case "1.8.8":
        Main.getInstance().getLogger().info("Detected server version: 1.8.8, using 1.8.8 adapters");
        version = new V1_8_R3(this);
        break;
      default:
        Main.getInstance().getLogger().severe("Your server version " + Bukkit.getServer().getBukkitVersion().split("-")[0] + " is currently unsupported");
        Main.getInstance().getServer().getPluginManager().disablePlugin(this);
    }

    getCommand("replay").setExecutor(new ReplayCommand());
    Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
  }

  @Override
  public void onDisable() {
  }

  public static Main getInstance() {
    return instance;
  }

  public static NMS getVersionAdapter() {
    return version;
  }
}
