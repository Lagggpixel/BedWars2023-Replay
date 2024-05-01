package me.lagggpixel.replay.listeners;

import me.lagggpixel.replay.support.nms.data.Cache;
import me.lagggpixel.replay.support.nms.data.IFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author Kiiya
 * @since April 28, 2024
 */
public class BlockPlaceListener implements Listener {

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    Player player = event.getPlayer();
    if (Cache.recordingPlayers.contains(player)) {
      IFrame frame = Cache.replays.get(player).getFrames().get(Cache.replays.get(player).getFrames().size() - 1);
      frame.setItemInHand(player.getItemInHand());
      frame.setPlacing(true);
      frame.setBlockLocation(event.getBlock().getLocation());
    }
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    if (Cache.recordingPlayers.contains(player)) {
      IFrame frame = Cache.replays.get(player).getFrames().get(Cache.replays.get(player).getFrames().size() - 1);
      frame.setItemInHand(player.getItemInHand());
      frame.setHitting(true);
      frame.setBlockLocation(event.getBlock().getLocation());
    }
  }

  @EventHandler
  public void onHit(PlayerInteractEvent e) {
    Player player = e.getPlayer();
    if (e.getAction() != Action.LEFT_CLICK_AIR) return;

    if (Cache.recordingPlayers.contains(player)) {
      IFrame frame = Cache.replays.get(player).getFrames().get(Cache.replays.get(player).getFrames().size() - 1);
      frame.setItemInHand(player.getItemInHand());
      frame.setHitting(true);
    }
  }

  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player) e.getEntity();
      if (Cache.recordingPlayers.contains(player)) {
        IFrame frame = Cache.replays.get(player).getFrames().get(Cache.replays.get(player).getFrames().size() - 1);
        frame.setDamaged(true);
      }
    }
  }

  @EventHandler
  public void onItemPickup(PlayerPickupItemEvent e) {
    Player player = e.getPlayer();
    if (Cache.recordingPlayers.contains(player)) {
      IFrame frame = Cache.replays.get(player).getFrames().get(Cache.replays.get(player).getFrames().size() - 1);
      frame.setPickupItem(e.getItem());
    }
  }

  @EventHandler
  public void onItemDrop(PlayerDropItemEvent e) {
    Player player = e.getPlayer();
    if (Cache.recordingPlayers.contains(player)) {
      IFrame frame = Cache.replays.get(player).getFrames().get(Cache.replays.get(player).getFrames().size() - 1);
      frame.setDropItem(e.getItemDrop());
    }
  }
}