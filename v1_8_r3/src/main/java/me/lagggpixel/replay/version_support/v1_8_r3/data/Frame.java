package me.lagggpixel.replay.version_support.v1_8_r3.data;

import me.lagggpixel.replay.version_support.nms.data.IFrame;
import me.lagggpixel.replay.version_support.v1_8_r3.V1_8_R3;
import net.minecraft.server.v1_8_R3.EntityItem;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

/**
 * @author Kiiya
 * @since April 28, 2024
 */
public class Frame extends IFrame {

  public Frame(Player player) {
    super(player);
  }

  @Override
  public void setPickupItem(Item pickupItem) {
    EntityItem entityItem = new EntityItem(((CraftItem) pickupItem).getHandle().getWorld(), pickupItem.getLocation().getX(), pickupItem.getLocation().getY(), pickupItem.getLocation().getZ(), CraftItemStack.asNMSCopy(pickupItem.getItemStack()));
    this.pickupItem = new CraftItem(((CraftServer) V1_8_R3.getPlugin().getServer()), entityItem);
  }
}