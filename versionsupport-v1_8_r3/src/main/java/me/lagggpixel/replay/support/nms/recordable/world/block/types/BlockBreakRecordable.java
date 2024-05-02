package me.lagggpixel.replay.support.nms.recordable.world.block.types;

import me.lagggpixel.replay.api.recordable.world.block.types.IBlockBreakRecordable;
import me.lagggpixel.replay.support.nms.recordable.world.block.BlockRecordable;
import org.bukkit.block.Block;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 02, 2024
 */
public class BlockBreakRecordable extends BlockRecordable implements IBlockBreakRecordable {

  public BlockBreakRecordable(UUID uuid, Block block) {
    super(uuid, block);
  }

}
