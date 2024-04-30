package me.lagggpixel.replay.nms.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IUtility {

  /**
   * @param packet Packet<PacketListenerPlayOut> object
   */
  void sendPacket(Player p, Object packet);

  /**
   * @param packet Packet<PacketListenerPlayOut> object
   */
  void sendPlayersPacket(Object packet);

  void spawnArmorStand(Player p);

  void spawnVehicle(Player p);

  void getOnVehicle(Player p);


  /**
   * @return EntityArmorStand object
   */
  Object getVehicle(Player p);

  /**
   * @param as EntityArmorStand object
   * @return CraftArmorStand object
   */
  Object getCraftArmorStand(Object as);

  ItemStack getSkull(String url);

  /**
   * @return EntityPlayer object
   */
  Object createNpc(Player p, Location loc, String name);
}