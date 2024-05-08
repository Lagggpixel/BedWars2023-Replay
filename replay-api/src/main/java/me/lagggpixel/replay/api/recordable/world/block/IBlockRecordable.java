package me.lagggpixel.replay.api.recordable.world.block;

import me.lagggpixel.replay.api.data.Vector3i;

import java.util.UUID;

public interface IBlockRecordable {

  /**
   * Gets the uuid of the player who triggered the block action
   * @return The uuid of the player
   */
  UUID getUuid();


  /**
   * Gets the location of the block
   * @see Vector3i
   * @return The Vector3i object for the location of the block
   */
  Vector3i getBlockLocation();

  /**
   * Gets the byte representing the action type
   * 0001 -> Break
   * 0010 -> Place
   * 0011 -> Interact
   * @return Byte representing the action type
   */
  byte getActionType();
}
