package me.lagggpixel.support.version;


import me.lagggpixel.replay.nms.NMS;
import me.lagggpixel.support.version.utils.Utility;
import org.bukkit.plugin.Plugin;

public final class V1_8_R3 implements NMS {

  private static V1_8_R3 instance;
  private static Plugin plugin;
  private final Utility utility;

  public V1_8_R3(Plugin plugin) {
    V1_8_R3.plugin = plugin;
    this.utility = new Utility();
  }

  public static V1_8_R3 getInstance() {
    return instance;
  }

  public static Plugin getPlugin() {
    return plugin;
  }

  @Override
  public Utility getUtility() {
    return this.utility;
  }
}
