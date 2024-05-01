package me.lagggpixel.replay.api.data;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public class Vector3d {
  protected final double x;
  protected final double y;
  protected final double z;

  public Vector3d(double var1, double var2, double var3) {
    this.x = var1;
    this.y = var2;
    this.z = var3;
  }

  public boolean equals(Object var1) {
    if (!(var1 instanceof Vector3d)) {
      return false;
    } else {
      Vector3d var2 = (Vector3d)var1;
      return this.x == var2.x && this.y == var2.y && this.z == var2.z;
    }
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getZ() {
    return this.z;
  }
}
