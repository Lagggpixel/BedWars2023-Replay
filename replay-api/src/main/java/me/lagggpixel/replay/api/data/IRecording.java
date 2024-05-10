package me.lagggpixel.replay.api.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lagggpixel
 * @since May 10, 2024
 */
public interface IRecording {

  Map<Integer, IFrame> getFrames();

  void add(Integer tick, IFrame frame);

  void add(IFrame... frames);

  void addAsList(HashMap<Integer, IFrame> framesList);

  IFrame getFrame(Integer tick);
}
