package me.lagggpixel.replay.api.recordable.entity.player.recordables;

import me.lagggpixel.replay.api.data.Camera;
import me.lagggpixel.replay.api.data.Vector3d;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public class PlayerInformation {

  private final Vector3d location;
  private final Camera camera;
  private final boolean isSneaking;
  private final boolean isBlocking;

  public PlayerInformation(UUID uuid) {
    Player player = Bukkit.getPlayer(uuid);
    if (player == null) {
      throw new RuntimeException("Player cannot be null when creating a IPlayerInformation instance");
    }
    this.location = new Vector3d(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    this.camera = new Camera(player.getLocation().getYaw(), player.getLocation().getPitch());
    this.isSneaking = player.isSneaking();
    this.isBlocking = player.isBlocking();
  }

  public Vector3d getLocation() {
    return location;
  }

  public Camera getCamera() {
    return camera;
  }

  public boolean isSneaking() {
    return isSneaking;
  }

  public boolean isBlocking() {
    return isBlocking;
  }
}
