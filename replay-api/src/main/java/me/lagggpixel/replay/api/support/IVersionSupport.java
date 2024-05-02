package me.lagggpixel.replay.api.support;

import me.lagggpixel.replay.api.recordable.entity.player.recordables.IEquipment;
import me.lagggpixel.replay.api.recordable.entity.player.recordables.PlayerInformation;
import me.lagggpixel.replay.api.recordable.world.block.types.IBlockBreakRecordable;
import me.lagggpixel.replay.api.recordable.world.block.types.IBlockPlaceRecordable;
import org.bukkit.block.Block;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public interface IVersionSupport {

  /**
   * Gets the player's information
   * @see PlayerInformation
   * @param uuid The uuid of the player
   * @return PlayerInformation instancing containing the player information
   */
  default PlayerInformation getPlayerInformation(UUID uuid) {
    return new PlayerInformation(uuid);
  }

  /**
   * Gets the player's current equipment
   * @see IEquipment
   * @param uuid The uuid of the player
   * @return Equipment object containing data about the player's equipment
   */
  IEquipment getEquipment(UUID uuid);

  /**
   * Creates a new instance of IBlockBreakRecordable
   * @see IBlockBreakRecordable
   * @param uuid The uuid of the player breaking the block
   * @return IBlockBreakRecordable instance containing the information regarding the block break
   */
  IBlockBreakRecordable getBlockBreak(UUID uuid, Block block);

  /**
   * Creates a new instance of IBlockPlaceRecordable
   * @see IBlockPlaceRecordable
   * @param uuid The uuid of the player placing the block
   * @return IBlockPlaceRecordable instance containing the information regarding the block place
   */
  IBlockPlaceRecordable getBlockPlace(UUID uuid, Block block);
}
