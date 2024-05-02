package me.lagggpixel.replay.support.nms.recordable.world.block;

import me.lagggpixel.replay.api.data.Vector3i;
import me.lagggpixel.replay.api.recordable.world.block.IBlockRecordable;
import me.lagggpixel.replay.support.nms.recordable.world.WorldRecordable;
import org.bukkit.block.Block;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public abstract class BlockRecordable extends WorldRecordable implements IBlockRecordable {

  protected final UUID uuid;
  protected final Vector3i location;

  public BlockRecordable(UUID uuid, Block block) {
    this.uuid = uuid;
    this.location = new Vector3i(block.getX(), block.getY(), block.getZ());
  }

  @Override
  public UUID getUuid() {
    return null;
  }

  @Override
  public Vector3i getBlockLocation() {
    return null;
  }
}
