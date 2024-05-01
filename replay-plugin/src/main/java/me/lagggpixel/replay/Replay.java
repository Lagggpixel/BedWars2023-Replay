package me.lagggpixel.replay;

import me.lagggpixel.replay.api.IReplay;
import me.lagggpixel.replay.api.IVersionSupport;
import org.bukkit.plugin.java.JavaPlugin;

public final class Replay extends JavaPlugin implements IReplay {

  private static Replay instance;
  private static IVersionSupport versionSupport;

  @Override
  public void onEnable() {

    instance = this;

  }

  @Override
  public void onDisable() {
  }

  public static Replay getInstance() {
    return instance;
  }

  public static IVersionSupport getVersionSupport() {
      return versionSupport;
  }
}
