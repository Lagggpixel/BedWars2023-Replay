package me.lagggpixel.replay.support.nms.recordable.world.block;

import me.lagggpixel.replay.api.data.Vector3i;
import me.lagggpixel.replay.api.recordable.world.block.IBlockRecordable;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public class BlockRecordable implements IBlockRecordable {

  private byte actionType;
  private final UUID uuid;
  private final Vector3i location;

  // Block dig data
  private byte direction;
  private byte digType;

  // Block place data
  private short materialId;
  private int face;

  private BlockRecordable(UUID uuid, Vector3i location, byte actionType) {
    this.uuid = uuid;
    this.location = location;
    this.actionType = actionType;
  }

  public static BlockRecordable createBlockPlaceRecordable(UUID uuid, Vector3i location, short materialId, int face) {
    BlockRecordable recordable = new BlockRecordable(uuid, location, (byte) 0b0010);
    recordable.setMaterialId(materialId);
    recordable.setFace(face);
    return recordable;
  }

  private void setFace(int face) {
    this.face = face;
  }

  private void setMaterialId(short materialId) {
    this.materialId = materialId;
  }

  public static BlockRecordable createBlockDigRecordable(UUID uuid, Vector3i location, byte direction, byte digType) {
    BlockRecordable recordable = new BlockRecordable(uuid, location, (byte) 0b0001);
    recordable.setDirection(direction);
    recordable.setDigType(digType);
    return recordable;
  }

  private void setDirection(byte direction) {
    this.direction = direction;
  }

  private void setDigType(byte digType) {
    this.digType = digType;
  }

  @Override
  public UUID getUuid() {
    return uuid;
  }

  @Override
  public Vector3i getBlockLocation() {
    return location;
  }

  @Override
  public byte getActionType() {
    return actionType;
  }
}
