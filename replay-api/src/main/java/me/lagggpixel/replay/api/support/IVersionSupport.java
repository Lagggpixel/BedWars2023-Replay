package me.lagggpixel.replay.api.support;

import me.lagggpixel.replay.api.recordable.entity.player.recordables.IEquipment;
import me.lagggpixel.replay.api.recordable.entity.player.recordables.PlayerInformation;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public interface IVersionSupport {

  PlayerInformation getPlayerInformation(UUID uuid);

  /**
   * Gets the player's current equipment
   * @param uuid The uuid of the player
   * @return Equipment object containing data about the player's equipment
   */
  IEquipment getEquipment(UUID uuid);


}
