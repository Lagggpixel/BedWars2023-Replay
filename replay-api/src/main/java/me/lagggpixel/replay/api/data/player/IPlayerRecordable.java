package me.lagggpixel.replay.api.data.player;

import me.lagggpixel.replay.api.data.Vector3d;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public abstract class IPlayerRecordable {
  protected Vector3d location;
  protected float pitch;
  protected float yaw;
  protected ItemStack itemInHand;
  // 0 - > null
  // 1 - > leather
  // 2 - > chain-mail
  // 3 - > iron
  // 4 - > diamond
  // 5 - > gold
  // 6 - > nether-rite
  // Note if nether-rite is not available in the current version, diamond will be displayed instead
  protected byte helmet = 0b0;
  protected byte chestplate = 0b0;
  protected byte leggings = 0b0;
  protected byte boots = 0b0;
  protected boolean isBlocking = false;
  protected boolean isSneaking = false;
}
