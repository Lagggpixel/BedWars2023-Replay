package me.lagggpixel.replay.listeners;

import me.lagggpixel.replay.Replay;
import me.lagggpixel.replay.api.recordable.world.block.types.IBlockPlaceRecordable;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 02, 2024
 */
public class BlockPlaceListener implements Listener {

  public BlockPlaceListener() {
    Replay.getInstance().getServer().getPluginManager().registerEvents(this, Replay.getInstance());
  }

  @EventHandler
  public void BlockPlaceEvent(BlockPlaceEvent event) {
    Block block = event.getBlockPlaced();
    Player player = event.getPlayer();
    UUID uuid = player.getUniqueId();
    IBlockPlaceRecordable blockPlaceRecordable = Replay.getVersionSupport().getBlockPlace(uuid, block);
    // todo - store this recordable
  }
}
