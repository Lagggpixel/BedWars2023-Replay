package me.lagggpixel.support.version.tasks;

import me.lagggpixel.support.version.V1_8_R3;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Kiiya
 * @since April 28, 2024
 */
public class CraftArmorRotate implements Runnable {
  private Player p;

  private EntityArmorStand as;

  private Location loc;

  private Location pLoc;

  private boolean up = false;

  public CraftArmorRotate(Player p, EntityArmorStand as, Location loc) {
    this.p = p;
    this.as = as;
    this.as.yaw = 0.0F;
    this.loc = loc;
    this.pLoc = p.getLocation();
  }

  public void run() {
    if (this.up) {
      if (this.as.yaw >= 360.0F)
        this.up = false;
      if (this.as.yaw > 320.0F) {
        this.as.yaw++;
      } else if (this.as.yaw > 290.0F) {
        this.as.yaw += 2.0F;
      } else if (this.as.yaw > 240.0F) {
        this.as.yaw += 3.0F;
      } else {
        this.as.yaw += 4.0F;
      }
    } else {
      if (this.as.yaw < -120.0F) {
        this.as.yaw -= 4.0F;
      } else if (this.as.yaw < -90.0F) {
        this.as.yaw -= 3.0F;
      } else if (this.as.yaw < -70.0F) {
        this.as.yaw -= 2.0F;
      } else {
        this.as.yaw--;
      }
      if (this.as.yaw <= -360.0F)
        this.up = true;
    }
    PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(this.as.getId(), MathHelper.floor(this.loc.getX() * 32.0D), MathHelper.floor(this.loc.getY() * 32.0D), MathHelper.floor(this.loc.getZ() * 32.0D), (byte)0, (byte)0, false);
    PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook lookPacket = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(this.as.getId(), (byte)0, (byte)0, (byte)0, (byte)(int)this.as.yaw, (byte)0, false);
    V1_8_R3.getInstance().getUtility().sendPlayersPacket(teleportPacket);
    V1_8_R3.getInstance().getUtility().sendPlayersPacket(lookPacket);
  }
}
