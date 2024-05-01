package me.lagggpixel.replay.support.nms.data.player;

import me.lagggpixel.replay.api.data.Vector3d;
import me.lagggpixel.replay.api.data.player.IPlayerRecordable;
import me.lagggpixel.replay.support.nms.V1_8_R3;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Item;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

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
    byte[] armourArray = getArmourBinary(Arrays.stream(player.inventory.armor).map(itemStack -> Item.getId(itemStack.getItem())).collect(Collectors.toList()));
    this.helmet = armourArray[0];
    this.chestplate = armourArray[1];
    this.leggings = armourArray[2];
    this.boots = armourArray[3];
    this.itemInHand = CraftItemStack.asBukkitCopy(player.inventory.getItemInHand());
    this.isSneaking = player.isSneaking();
    this.isBlocking = player.isBlocking();
  }

  public static PlayerRecordable createPlayerRecordable(UUID uuid) {
    return new PlayerRecordable(uuid);
  }

}
