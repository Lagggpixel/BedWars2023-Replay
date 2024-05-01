package me.lagggpixel.replay.api.data.player;

import me.lagggpixel.replay.api.data.Vector3d;
import org.bukkit.inventory.ItemStack;

import java.util.List;

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

  protected byte[] getArmourBinary(List<Integer> collection) {
    byte[] array = new byte[4];
    switch (collection.get(0)) {
      case 298:
        array[0] = 0b0001;
        break;
      case 302:
        array[0] = 0b0010;
        break;
      case 306:
        array[0] = 0b0011;
        break;
      case 310:
        array[0] = 0b0100;
        break;
      case 314:
        array[0] = 0b0101;
        break;
      default:
        array[0] = 0b0000;
    }
    switch (collection.get(1)) {
      case 299:
        array[1] = 0b0001;
        break;
      case 303:
        array[1] = 0b0010;
        break;
      case 307:
        array[1] = 0b0011;
        break;
      case 311:
        array[1] = 0b0100;
        break;
      case 315:
        array[1] = 0b0101;
        break;
      default:
        array[1] = 0b0000;
    }
    switch (collection.get(2)) {
      case 300:
        array[2] = 0b0001;
        break;
      case 304:
        array[2] = 0b0010;
        break;
      case 308:
        array[2] = 0b0011;
        break;
      case 312:
        array[2] = 0b0100;
        break;
      case 316:
        array[2] = 0b0101;
        break;
      default:
        array[2] = 0b0000;
    }
    switch (collection.get(3)) {
      case 301:
        array[3] = 0b0001;
        break;
      case 305:
        array[3] = 0b0010;
        break;
      case 309:
        array[3] = 0b0011;
        break;
      case 313:
        array[3] = 0b0100;
        break;
      case 317:
        array[3] = 0b0101;
        break;
      default:
        array[3] = 0b0000;
    }
    return array;
  }
}
