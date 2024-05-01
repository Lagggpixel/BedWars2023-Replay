package me.lagggpixel.replay.support.nms.data.player;

import me.lagggpixel.replay.api.data.Vector3d;
import me.lagggpixel.replay.api.data.player.IPlayerRecordable;
import me.lagggpixel.replay.support.nms.V1_8_R3;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Item;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public class PlayerRecordable extends IPlayerRecordable {

  private PlayerRecordable(UUID uuid) {
    EntityPlayer player = V1_8_R3.getInstance().getServer().getHandle().a(uuid);
    this.location = new Vector3d(player.locX, player.locY, player.locZ);
    this.pitch = player.pitch;
    this.yaw = player.yaw;
    switch (Item.getId(player.inventory.armor[0].getItem())) {
      case 298:
        this.helmet = 0b0001;
        break;
      case 302:
        this.helmet = 0b0010;
        break;
      case 306:
        this.helmet = 0b0011;
        break;
      case 310:
        this.helmet = 0b0100;
        break;
      case 314:
        this.helmet = 0b0101;
        break;
      default:
        this.helmet = 0b0000;
    }
    switch (Item.getId(player.inventory.armor[1].getItem())) {
      case 299:
        this.chestplate = 0b0001;
        break;
      case 303:
        this.chestplate = 0b0010;
        break;
      case 307:
        this.chestplate = 0b0011;
        break;
      case 311:
        this.chestplate = 0b0100;
        break;
      case 315:
        this.chestplate = 0b0101;
        break;
      default:
        this.chestplate = 0b0000;
    }
    switch (Item.getId(player.inventory.armor[2].getItem())) {
      case 300:
        this.leggings = 0b0001;
        break;
      case 304:
        this.leggings = 0b0010;
        break;
      case 308:
        this.leggings = 0b0011;
        break;
      case 312:
        this.leggings = 0b0100;
        break;
      case 316:
        this.leggings = 0b0101;
        break;
      default:
        this.leggings = 0b0000;
    }
    switch (Item.getId(player.inventory.armor[3].getItem())) {
      case 301:
        this.boots = 0b0001;
        break;
      case 305:
        this.boots = 0b0010;
        break;
      case 309:
        this.boots = 0b0011;
        break;
      case 313:
        this.boots = 0b0100;
        break;
      case 317:
        this.boots = 0b0101;
        break;
      default:
        this.boots = 0b0000;
    }
    this.itemInHand = CraftItemStack.asBukkitCopy(player.inventory.getItemInHand());
    this.isSneaking = player.isSneaking();
    this.isBlocking = player.isBlocking();
  }

  public static PlayerRecordable createPlayerRecordable(UUID uuid) {
    return new PlayerRecordable(uuid);
  }

}
