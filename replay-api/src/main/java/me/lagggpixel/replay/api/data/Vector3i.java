package me.lagggpixel.replay.api.data;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public class Vector3i {
  protected final int x;
  protected final int y;
  protected final int z;

  public Vector3i(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getZ() {
    return this.z;
  }

  public boolean equals(Object var1) {
    if (!(var1 instanceof Vector3i)) {
      return false;
    } else {
      Vector3i var2 = (Vector3i)var1;
      return this.x == var2.x && this.y == var2.y && this.z == var2.z;
    }
  }
}
