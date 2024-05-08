package me.lagggpixel.replay.support.nms.recordable.world.block.types;

import me.lagggpixel.replay.api.data.Vector3i;
import me.lagggpixel.replay.api.recordable.world.block.types.IBlockPlacePacketRecordable;
import me.lagggpixel.replay.support.nms.recordable.world.block.BlockRecordable;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 08, 2024
 */
public class BlockPlacePacketRecordable extends BlockRecordable implements IBlockPlacePacketRecordable {

  private short materialId;
  private int face;

  public BlockPlacePacketRecordable(UUID uuid, Vector3i location, short materialId, int face) {
    super(uuid, location);
    this.materialId = materialId;
    this.face = face;
  }
}
