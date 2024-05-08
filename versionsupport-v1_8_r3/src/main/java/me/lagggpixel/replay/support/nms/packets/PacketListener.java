package me.lagggpixel.replay.support.nms.packets;

import me.lagggpixel.replay.api.data.Vector3i;
import me.lagggpixel.replay.api.events.PacketReadEvent;
import me.lagggpixel.replay.api.events.PacketWriteEvent;
import me.lagggpixel.replay.api.packets.IPacketListener;
import me.lagggpixel.replay.support.nms.recordable.world.block.BlockRecordable;
import me.lagggpixel.replay.support.nms.v1_8_R3;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.UUID;
import java.util.logging.Level;

/**
 * @author Kiiya
 * @since May 07, 2024
 */
public class PacketListener implements IPacketListener {
  // Packets are handled by the server, so we need to register a listener to the server.
  // This class is registered in InjectorHandler.java.
  // Info about packets: https://wiki.vg/Protocol || Outdated (E.j: You can't find PacketPlayInSteerVehicle).
  // How to find packets: import net.minecraft.server.<version>.<packet>;
  // Example: import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
  // Note: You can't find packets in the Spigot API, you need to find them in the Minecraft server API.

  private static boolean isInitialized = false;

  // SINGLETON INSTANCE
  public PacketListener() {
    if (isInitialized) {
      throw new RuntimeException("PacketListener is a singleton instance, it can not be initialized for more than once");
    }
    isInitialized = true;
    v1_8_R3.getInstance().getPlugin().getLogger().log(Level.INFO, "&aPacket listener registered.");
  }

  /**
   * PacketWriteEvent is called when a packet is sent to the client.
   *
   * @param e The PacketWriteEvent instance
   * @see PacketWriteEvent
   */
  @Override
  @EventHandler
  public void onPacketWrite(PacketWriteEvent e) {
    // Utility.log("&bPacket write: " + e.getPacket().getClass().getSimpleName());
  }

  /**
   * PacketReadEvent is called when a packet is received from the client.
   *
   * @param e The PacketReadEvent instance
   * @see PacketReadEvent
   */
  @Override
  @EventHandler
  public void onPacketRead(PacketReadEvent e) {
    // Utility.log("&bPacket read: " + e.getPacket().getClass().getSimpleName());

    Player p = e.getPlayer();
    Object pac = e.getPacket();

    if (pac instanceof PacketPlayInBlockDig) {
      PacketPlayInBlockDig packet = (PacketPlayInBlockDig) pac;
      handleBlockDigPacket(p, packet);
      return;
    }
    if (pac instanceof PacketPlayInBlockPlace) {
      PacketPlayInBlockPlace packet = (PacketPlayInBlockPlace) pac;
      handleBlockPlacePacket(p, packet);
    }
  }

  private void handleBlockPlacePacket(Player p, PacketPlayInBlockPlace packet) {
    BlockPosition blockPos = packet.a();
    UUID uuid = p.getUniqueId();
    int posX = blockPos.getX();
    int posY = blockPos.getY();
    int posZ = blockPos.getZ();
    Vector3i location = new Vector3i(posX, posY, posZ);
    // note this will break if there's more than 512 items
    short id = (short) Item.getId(packet.getItemStack().getItem());
    int face = packet.getFace();
    BlockRecordable recordable =
        BlockRecordable.createBlockPlaceRecordable(uuid, location, id, face);
    // todo - store this recordable
  }

  private void handleBlockDigPacket(Player p, PacketPlayInBlockDig packet) {
    BlockPosition blockPos = packet.a();
    UUID uuid = p.getUniqueId();
    int posX = blockPos.getX();
    int posY = blockPos.getY();
    int posZ = blockPos.getZ();
    Vector3i location = new Vector3i(posX, posY, posZ);
    byte dirByte = serializeDirection(packet.b());
    byte digTypeByte = serializeDigType(packet.c());

    BlockRecordable recordable =
        BlockRecordable.createBlockDigRecordable(
            uuid,
            location,
            dirByte,
            digTypeByte);
    // todo - store this recordable
  }

  /**
   * Serializes the PacketPlayInBlockDig.EnumPlayerDigType object into a byte
   * <p>
   * START_DESTROY_BLOCK - 0001 <p>
   * ABORT_DESTROY_BLOCK - 0010 <p>
   * DROP_ITEM - 0011 <p>
   * DROP_ALL_ITEMS - 0100 <p>
   * RELEASE_USE_ITEM - 0101 <p>
   * STOP_DESTROY_BLOCK - 0110
   *
   * @param digType The PacketPlayInBlockDig.EnumPlayerDigType to be serialized
   * @return The byte representing the PacketPlayInBlockDig.EnumPlayerDigType object
   */
  private byte serializeDigType(PacketPlayInBlockDig.EnumPlayerDigType digType) {
    switch (digType) {
      case START_DESTROY_BLOCK:
        return 0b0001;
      case ABORT_DESTROY_BLOCK:
        return 0b0010;
      case DROP_ITEM:
        return 0b0011;
      case DROP_ALL_ITEMS:
        return 0b0100;
      case RELEASE_USE_ITEM:
        return 0b0101;
      case STOP_DESTROY_BLOCK:
        return 0b0110;
      default:
        return 0b0000;
    }
  }

  /**
   * Serializes the EnumDirection object into a byte
   * <p>
   * UP - 0001 <p>
   * DOWN - 0010 <p>
   * NORTH - 0011 <p>
   * SOUTH - 0100 <p>
   * EAST - 0101 <p>
   * WEST - 0110
   *
   * @param direction The EnumDirection to be serialized
   * @return The byte representing the EnumDirection object
   */
  private byte serializeDirection(EnumDirection direction) {
    switch (direction) {
      case UP:
        return 0b0001;
      case DOWN:
        return 0b0010;
      case NORTH:
        return 0b0011;
      case SOUTH:
        return 0b0100;
      case EAST:
        return 0b0101;
      case WEST:
        return 0b0110;
      default:
        return 0b0000;
    }
  }
}
