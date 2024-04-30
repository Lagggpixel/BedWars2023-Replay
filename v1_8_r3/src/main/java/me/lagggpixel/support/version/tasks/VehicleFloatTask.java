package me.lagggpixel.support.version.tasks;

import net.minecraft.server.v1_8_R3.EntityArmorStand;

/**
 * @author Lagggpixel
 * @since April 28, 2024
 */
public class VehicleFloatTask implements Runnable {
  private EntityArmorStand as;

  private boolean up = true;

  public VehicleFloatTask(EntityArmorStand as) {
    this.as = as;
    this.as.motY = 0.0D;
  }

  public void run() {}
}