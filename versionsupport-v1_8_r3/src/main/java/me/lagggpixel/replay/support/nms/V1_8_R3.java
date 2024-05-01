package me.lagggpixel.replay.support.nms;

import me.lagggpixel.replay.api.IReplay;
import me.lagggpixel.replay.api.IVersionSupport;
import me.lagggpixel.replay.api.data.player.IPlayerRecordable;
import me.lagggpixel.replay.support.nms.data.player.PlayerRecordable;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

import java.util.UUID;

public class V1_8_R3 implements IVersionSupport {

  private static V1_8_R3 instance;
  private static IReplay plugin;
  private final CraftServer server;

  public V1_8_R3(IReplay plugin) {
    V1_8_R3.instance = this;
    V1_8_R3.plugin = plugin;
    this.server = (CraftServer) Bukkit.getServer();
  }

  public static IReplay getPlugin() {
    return plugin;
  }

  public static V1_8_R3 getInstance() {
    return instance;
  }

  public CraftServer getServer() {
    return server;
  }

  @Override
  public IPlayerRecordable createPlayerRecordable(UUID uuid) {
    return PlayerRecordable.createPlayerRecordable(uuid);
  }
}
