package me.lagggpixel.replay.api.data;

import me.lagggpixel.replay.api.recordable.IRecordable;

import java.util.List;

public interface IFrame {
  List<IRecordable> getRecordables();

  void add(IRecordable... recordables);

  void addAsList(List<IRecordable> recordableList);
}
