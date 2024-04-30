package me.lagggpixel.support.version.tasks;

import me.lagggpixel.support.version.V1_8_R3;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Lagggpixel
 * @since April 28, 2024
 */

public class FollowPet implements Runnable {
  private final Player p;

  private final EntityArmorStand as;

  boolean up = true;

  public FollowPet(Player p, EntityArmorStand as) {
    this.p = p;
    this.as = as;
  }

  public void run() {
    Location loc = this.p.getLocation();
    if (this.up) {
      this.as.motY++;
      if (this.as.motY >= 5.0D)
        this.up = false;
    } else {
      this.as.motY--;
      if (this.as.motY <= -5.0D)
        this.up = true;
    }
    this.as.yaw = loc.getYaw();
    PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(this.as.getId(), MathHelper.floor(loc.getX() * 32.0D), MathHelper.floor((loc.getY() + 1.5D) * 32.0D), MathHelper.floor(loc.getZ() * 32.0D), (byte)(int)this.as.yaw, (byte)(int)this.as.pitch, false);
    PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook lookPacket = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(this.as.getId(), (byte)0, (byte)(int)this.as.motY, (byte)0, (byte)(int)this.as.yaw, (byte)0, false);
    V1_8_R3.getInstance().getUtility().sendPlayersPacket(teleportPacket);
    V1_8_R3.getInstance().getUtility().sendPlayersPacket(lookPacket);
  }
}

