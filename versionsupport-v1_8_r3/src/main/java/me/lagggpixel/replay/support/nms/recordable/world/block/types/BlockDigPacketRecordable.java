package me.lagggpixel.replay.support.nms.recordable.world.block.types;

import me.lagggpixel.replay.api.data.Vector3i;
import me.lagggpixel.replay.api.recordable.world.block.types.IBlockDigPacketRecordable;
import me.lagggpixel.replay.support.nms.recordable.world.block.BlockRecordable;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 02, 2024
 */
public class BlockDigPacketRecordable extends BlockRecordable implements IBlockDigPacketRecordable {

  byte direction;
  byte digType;

  public BlockDigPacketRecordable(UUID uuid, Vector3i location, byte direction, byte digType) {
    super(uuid, location);

    this.direction = direction;
    this.digType = digType;
  }

}
