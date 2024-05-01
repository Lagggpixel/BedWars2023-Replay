package me.lagggpixel.replay.support.nms.v1_8_r3;


import me.lagggpixel.replay.support.nms.v1_8_r3.data.Frame;
import me.lagggpixel.replay.support.nms.v1_8_r3.data.Replay;
import me.lagggpixel.replay.support.nms.v1_8_r3.utils.Utility;
import me.lagggpixel.replay.support.nms.NMS;
import me.lagggpixel.replay.support.nms.data.IFrame;
import me.lagggpixel.replay.support.nms.data.IReplay;
import org.bukkit.entity.Player;
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

  @Override
  public IReplay createNewReplay(Player player) {
    return new Replay(player);
  }

  @Override
  public IFrame createNewFrame(Player player) {
    return new Frame(player);
  }
}
