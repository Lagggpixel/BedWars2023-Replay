package me.lagggpixel.replay.support.nms.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IUtility {

  String c(String s);

  /**
   * @param packet Packet<PacketListenerPlayOut> object
   */
  void sendPacket(Player p, Object packet);

  /**
   * @param packet Packet<PacketListenerPlayOut> object
   */
  void sendPlayersPacket(Object packet);

  /**
   * @return EntityPlayer object
   */
  Object createNpc(Player p, Location loc, String name);
}