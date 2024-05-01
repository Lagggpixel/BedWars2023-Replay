package me.lagggpixel.replay.api.data;

public class Camera {
    private final double yaw;
    private final double pitch;

    public Camera(double yaw, double pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public boolean equals(Object var1) {
        if (!(var1 instanceof Camera)) {
            return false;
        } else {
            Camera var2 = (Camera)var1;
            return this.yaw == var2.yaw && this.pitch == var2.pitch;
        }
    }
}
