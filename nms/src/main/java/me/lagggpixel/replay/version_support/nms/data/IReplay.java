package me.lagggpixel.replay.version_support.nms.data;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Lagggpixel
 * @since April 28, 2024
 */
public interface IReplay {
  List<IFrame> getFrames();

  void play(Player player);

  void startRecording();

  void stopRecording();
}
