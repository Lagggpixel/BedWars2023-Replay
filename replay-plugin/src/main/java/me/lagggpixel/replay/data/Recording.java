package me.lagggpixel.replay.data;

import me.lagggpixel.replay.Replay;
import me.lagggpixel.replay.api.data.IFrame;
import me.lagggpixel.replay.api.data.IRecording;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lagggpixel
 * @since May 10, 2024
 */
public class Recording implements IRecording {

  private final HashMap<Integer, IFrame> frames;

  public Recording(HashMap<Integer, IFrame> frames) {
    this.frames = frames;
  }

  public Recording(IFrame... frames) {
    int counter = 1;
    this.frames = new HashMap<>();
    for (IFrame frame : frames) {
      this.frames.put(counter, frame);
      counter++;
    }
  }

  public Recording() {
    frames = new HashMap<>();
  }

  @Override
  public Map<Integer, IFrame> getFrames() {
    return this.frames;
  }

  @Override
  public void add(Integer tick, IFrame frame) {
    this.frames.put(tick, frame);
  }

  @Override
  public void add(IFrame... frames) {
    int lastFrame = Collections.max(this.frames.keySet());
    for (IFrame frame : frames) {
      lastFrame++;
      this.add(lastFrame, frame);
    }
  }

  @Override
  public void addAsList(HashMap<Integer, IFrame> framesList) {
    this.frames.putAll(framesList);
  }

  @Override
  public IFrame getFrame(Integer tick) {
    return frames.get(tick);
  }

  public BukkitRunnable play(Player player) {
    BukkitRunnable runnable = new BukkitRunnable() {
      int tickCounter = 0;
      @Override
      public void run() {
        tickCounter++;
        getFrame(tickCounter).play(player);
      }

      public int getTickCounter() {
        return tickCounter;
      }

      public void setTickCounter(int tick) {
        tickCounter = tick;
      }
    };
    runnable.runTaskTimer(Replay.getInstance(), 0L, 1L);

    return runnable;
  }
}
