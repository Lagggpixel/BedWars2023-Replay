package me.lagggpixel.replay.api.recordable.world.block.types;

import me.lagggpixel.replay.api.recordable.world.block.IBlockRecordable;

/**
 * @author Lagggpixel
 * @since May 02, 2024
 */
public interface IBlockDigPacketRecordable extends IBlockRecordable {
  @Override
  default byte getActionType() {
    return 0b0000;
  }
}
