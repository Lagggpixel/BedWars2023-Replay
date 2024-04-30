package me.lagggpixel.replay.nms.data;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Lagggpixel
 * @since April 30, 2024
 */
public class IFrame {
  protected final Location location;
  protected Location blockLocation;
  protected ItemStack itemInHand;
  protected Item dropItem;
  protected Item pickupItem;
  protected ItemStack helmet;
  protected ItemStack chestplate;
  protected ItemStack leggings;
  protected ItemStack boots;
  protected boolean isHitting;
  protected boolean isPlacing;
  protected boolean isDamaged;
  protected boolean isBlocking;
  protected boolean isSneaking;

  public IFrame(Player player) {
    this.location = player.getLocation();
    this.blockLocation = null;
    this.helmet = player.getInventory().getHelmet();
    this.chestplate = player.getInventory().getChestplate();
    this.leggings = player.getInventory().getLeggings();
    this.boots = player.getInventory().getBoots();
    this.itemInHand = player.getItemInHand();
    this.pickupItem = null;
    this.dropItem = null;
    this.isHitting = false;
    this.isPlacing = false;
    this.isDamaged = false;
    this.isSneaking = player.isSneaking();
    this.isBlocking = player.isBlocking();
  }


  public Location getLocation() {
    return location;
  }

  public Location getBlockLocation() {
    return blockLocation;
  }

  public void setBlockLocation(Location blockLocation) {
    this.blockLocation = blockLocation;
  }

  public ItemStack getItemInHand() {
    return itemInHand;
  }

  public Item getPickupItem() {
    return pickupItem;
  }

  public Item getDropItem() {
    return dropItem;
  }

  public void setPickupItem(Item pickupItem) {
    throw new RuntimeException("Method must be overwritten");
  }

  public void setDropItem(Item dropItem) {
    this.dropItem = dropItem;
  }

  public ItemStack getHelmet() {
    return helmet;
  }

  public ItemStack getChestplate() {
    return chestplate;
  }

  public ItemStack getLeggings() {
    return leggings;
  }

  public ItemStack getBoots() {
    return boots;
  }

  public void setHelmet(ItemStack helmet) {
    this.helmet = helmet;
  }

  public void setChestplate(ItemStack chestplate) {
    this.chestplate = chestplate;
  }

  public void setLeggings(ItemStack leggings) {
    this.leggings = leggings;
  }

  public void setBoots(ItemStack boots) {
    this.boots = boots;
  }

  public void setItemInHand(ItemStack itemInHand) {
    this.itemInHand = itemInHand;
  }


  public boolean isHitting() {
    return isHitting;
  }

  public boolean isPlacing() {
    return isPlacing;
  }

  public boolean isDamaged() {
    return isDamaged;
  }

  public boolean isBlocking() {
    return isBlocking;
  }

  public boolean isSneaking() {
    return isSneaking;
  }

  public void setHitting(boolean hitting) {
    isHitting = hitting;
  }

  public void setPlacing(boolean placing) {
    isPlacing = placing;
  }

  public void setDamaged(boolean damaged) {
    isDamaged = damaged;
  }

  public void setBlocking(boolean blocking) {
    isBlocking = blocking;
  }

  public void setSneaking(boolean sneaking) {
    isSneaking = sneaking;
  }
}
