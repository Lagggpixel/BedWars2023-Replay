package me.lagggpixel.replay.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This is the event that is called when a packet is read from the server.
 *
 * @author Kiiya
 * @since May 07, 2024
 */
public class PacketReadEvent extends Event {
  public static final HandlerList handlers = new HandlerList();
  /**
   * The player that received the packet.
   */
  private final Player player;
  /**
   * The packet that was received.
   */
  private final Object packet;
  /**
   * Whether the event is cancelled.
   */
  private boolean cancelled = false;

  public PacketReadEvent(Player p, Object packet) {
    this.packet = packet;
    this.player = p;
  }

  /**
   * @return The player that received the packet.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * @return The packet that was received.
   */
  public Object getPacket() {
    return packet;
  }

  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }

  /**
   * @return Whether the event is cancelled.
   */
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}