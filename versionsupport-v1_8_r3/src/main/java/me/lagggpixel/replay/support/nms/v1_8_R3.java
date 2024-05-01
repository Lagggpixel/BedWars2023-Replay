package me.lagggpixel.replay.support.nms;

import org.bukkit.Bukkit;
import me.lagggpixel.replay.api.IReplay;
import me.lagggpixel.replay.api.support.IVersionSupport;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class v1_8_R3 implements IVersionSupport {
  private static v1_8_R3 instance;
  private static IReplay plugin;
  private final CraftServer server;

  public v1_8_R3(IReplay plugin) {
    this.server = (CraftServer) Bukkit.getServer();
    v1_8_R3.plugin = plugin;
    instance = this;
  }

  public static v1_8_R3 getInstance() {
    return instance;
  }

  public IReplay getPlugin() {
    return plugin;
  }

  public CraftServer getServer() {
    return server;
  }
}
