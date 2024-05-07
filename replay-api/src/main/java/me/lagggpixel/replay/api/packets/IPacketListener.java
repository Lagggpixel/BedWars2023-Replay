package me.lagggpixel.replay.api.packets;

import me.lagggpixel.replay.api.events.PacketReadEvent;
import me.lagggpixel.replay.api.events.PacketWriteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Lagggpixel
 * @since May 07, 2024
 */
public interface IPacketListener extends Listener {
  @EventHandler
  void onPacketWrite(PacketWriteEvent e);

  @EventHandler
  void onPacketRead(PacketReadEvent e);
}
