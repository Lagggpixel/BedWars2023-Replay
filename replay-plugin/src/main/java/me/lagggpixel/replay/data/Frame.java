package me.lagggpixel.replay.data;

import me.lagggpixel.replay.api.data.IFrame;
import me.lagggpixel.replay.api.recordable.IRecordable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frame implements IFrame {

  private final List<IRecordable> recordables;

  public Frame(List<IRecordable> recordables) {
    this.recordables = recordables;
  }

  public Frame(IRecordable... recordables) {
    this.recordables = Arrays.asList(recordables);
  }

  public Frame() {
    recordables = new ArrayList<>();
  }

  @Override
  public List<IRecordable> getRecordables() {
    return this.recordables;
  }

  @Override
  public void add(IRecordable... recordables) {
    this.recordables.addAll(Arrays.asList(recordables));
  }

  @Override
  public void addAsList(List<IRecordable> recordableList) {
    this.recordables.addAll(recordableList);
  }

  @Override
  public void play(Player player) {
    for (IRecordable recordable : recordables) {
      recordable.play(player);
    }
  }
}
