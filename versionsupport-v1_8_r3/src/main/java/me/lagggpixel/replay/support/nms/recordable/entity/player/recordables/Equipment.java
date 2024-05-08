package me.lagggpixel.replay.support.nms.recordable.entity.player.recordables;

import me.lagggpixel.replay.api.recordable.entity.player.recordables.IEquipment;
import me.lagggpixel.replay.support.nms.v1_8_R3;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemStack;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public class Equipment implements IEquipment {
  // Armour type mappings:
  //   0 - > null
  //   1 - > leather
  //   2 - > chain-mail
  //   3 - > iron
  //   4 - > diamond
  //   5 - > gold
  //   6 - > netherite

  private final short handId;
  private final byte handByteData;
  private final boolean isHandEnchanted;
  private final byte helmet;
  private final boolean isHelmetEnchanted;
  private final byte chestplate;
  private final boolean isChestplateEnchanted;
  private final byte leggings;
  private final boolean isLeggingsEnchanted;
  private final byte boots;
  private final boolean isBootsEnchanted;

  /**
   * @param uuid Player uuid
   */
  public Equipment(UUID uuid) {
    EntityPlayer player = v1_8_R3.getInstance().getServer().getHandle().a(uuid);

    ItemStack handItem = player.inventory.getItemInHand();
    // This will break if the number of items exceed 511
    this.handId = (short) Item.getId(handItem.getItem());
    // This will break if the damage of the item in hand exceeds 127
    this.handByteData = (byte) handItem.getData();
    this.isHandEnchanted = !handItem.getEnchantments().isEmpty();
    byte[] armour = getArmourBinary(
        Arrays.stream(player.inventory.armor)
            .map(itemStack -> Item.getId(itemStack.getItem()))
            .collect(Collectors.toList())
    );
    this.helmet = armour[0];
    this.isHelmetEnchanted = isItemStackEnchanted(player.inventory.armor[0]);
    this.chestplate = armour[1];
    this.isChestplateEnchanted = isItemStackEnchanted(player.inventory.armor[1]);
    this.leggings = armour[2];
    this.isLeggingsEnchanted = isItemStackEnchanted(player.inventory.armor[2]);
    this.boots = armour[3];
    this.isBootsEnchanted = isItemStackEnchanted(player.inventory.armor[3]);
  }

  @Override
  public org.bukkit.inventory.ItemStack getMainHand() {
    CraftItemStack itemStack = CraftItemStack.asNewCraftStack(Item.getById(handId));
    itemStack.setDurability(handByteData);
    if (isHandEnchanted) {
      itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    }
    return itemStack;
  }

  @Override
  public org.bukkit.inventory.ItemStack getOffhand() {
    return null;
  }

  @Override
  public org.bukkit.inventory.ItemStack getHelmet() {
    org.bukkit.inventory.ItemStack itemStack;
    switch (this.helmet) {
      case 0b0001:
        itemStack = new org.bukkit.inventory.ItemStack(Material.LEATHER_HELMET);
        break;
      case 0b0010:
        itemStack = new org.bukkit.inventory.ItemStack(Material.CHAINMAIL_HELMET);
        break;
      case 0b0011:
        itemStack = new org.bukkit.inventory.ItemStack(Material.IRON_HELMET);
        break;
      case 0b0100:
      case 0b0110:
        itemStack = new org.bukkit.inventory.ItemStack(Material.DIAMOND_HELMET);
        break;
      case 0b0101:
        itemStack = new org.bukkit.inventory.ItemStack(Material.GOLD_HELMET);
        break;
      default:
        return null;
    }
    if (isHelmetEnchanted) {
      itemStack.addUnsafeEnchantment(Enchantment.DURABILITY ,1);
    }
    return itemStack;
  }

  @Override
  public org.bukkit.inventory.ItemStack getChestplate() {
    org.bukkit.inventory.ItemStack itemStack;
    switch (this.chestplate) {
      case 0b0001:
        itemStack = new org.bukkit.inventory.ItemStack(Material.LEATHER_CHESTPLATE);
        break;
      case 0b0010:
        itemStack = new org.bukkit.inventory.ItemStack(Material.CHAINMAIL_CHESTPLATE);
        break;
      case 0b0011:
        itemStack = new org.bukkit.inventory.ItemStack(Material.IRON_CHESTPLATE);
        break;
      case 0b0100:
      case 0b0110:
        itemStack = new org.bukkit.inventory.ItemStack(Material.DIAMOND_CHESTPLATE);
        break;
      case 0b0101:
        itemStack = new org.bukkit.inventory.ItemStack(Material.GOLD_CHESTPLATE);
        break;
      default:
        return null;
    }
    if (isChestplateEnchanted) {
      itemStack.addUnsafeEnchantment(Enchantment.DURABILITY ,1);
    }
    return itemStack;
  }

  @Override
  public org.bukkit.inventory.ItemStack getLeggings() {
    org.bukkit.inventory.ItemStack itemStack;
    switch (this.leggings) {
      case 0b0001:
        itemStack = new org.bukkit.inventory.ItemStack(Material.LEATHER_LEGGINGS);
        break;
      case 0b0010:
        itemStack = new org.bukkit.inventory.ItemStack(Material.CHAINMAIL_LEGGINGS);
        break;
      case 0b0011:
        itemStack = new org.bukkit.inventory.ItemStack(Material.IRON_LEGGINGS);
        break;
      case 0b0100:
      case 0b0110:
        itemStack = new org.bukkit.inventory.ItemStack(Material.DIAMOND_LEGGINGS);
        break;
      case 0b0101:
        itemStack = new org.bukkit.inventory.ItemStack(Material.GOLD_LEGGINGS);
        break;
      default:
        return null;
    }
    if (isLeggingsEnchanted) {
      itemStack.addUnsafeEnchantment(Enchantment.DURABILITY ,1);
    }
    return itemStack;
  }

  @Override
  public org.bukkit.inventory.ItemStack getBoots() {
    org.bukkit.inventory.ItemStack itemStack;
    switch (this.boots) {
      case 0b0001:
        itemStack = new org.bukkit.inventory.ItemStack(Material.LEATHER_BOOTS);
        break;
      case 0b0010:
        itemStack = new org.bukkit.inventory.ItemStack(Material.CHAINMAIL_BOOTS);
        break;
      case 0b0011:
        itemStack = new org.bukkit.inventory.ItemStack(Material.IRON_BOOTS);
        break;
      case 0b0100:
      case 0b0110:
        itemStack = new org.bukkit.inventory.ItemStack(Material.DIAMOND_BOOTS);
        break;
      case 0b0101:
        itemStack = new org.bukkit.inventory.ItemStack(Material.GOLD_BOOTS);
        break;
      default:
        return null;
    }
    if (isBootsEnchanted) {
      itemStack.addUnsafeEnchantment(Enchantment.DURABILITY ,1);
    }
    return itemStack;
  }

  private byte[] getArmourBinary(List<Integer> collection) {
    byte[] array = new byte[4];
    switch (collection.get(0)) {
      case 298:
        array[0] = 0b0001;
        break;
      case 302:
        array[0] = 0b0010;
        break;
      case 306:
        array[0] = 0b0011;
        break;
      case 310:
        array[0] = 0b0100;
        break;
      case 314:
        array[0] = 0b0101;
        break;
      default:
        array[0] = 0b0000;
    }
    switch (collection.get(1)) {
      case 299:
        array[1] = 0b0001;
        break;
      case 303:
        array[1] = 0b0010;
        break;
      case 307:
        array[1] = 0b0011;
        break;
      case 311:
        array[1] = 0b0100;
        break;
      case 315:
        array[1] = 0b0101;
        break;
      default:
        array[1] = 0b0000;
    }
    switch (collection.get(2)) {
      case 300:
        array[2] = 0b0001;
        break;
      case 304:
        array[2] = 0b0010;
        break;
      case 308:
        array[2] = 0b0011;
        break;
      case 312:
        array[2] = 0b0100;
        break;
      case 316:
        array[2] = 0b0101;
        break;
      default:
        array[2] = 0b0000;
    }
    switch (collection.get(3)) {
      case 301:
        array[3] = 0b0001;
        break;
      case 305:
        array[3] = 0b0010;
        break;
      case 309:
        array[3] = 0b0011;
        break;
      case 313:
        array[3] = 0b0100;
        break;
      case 317:
        array[3] = 0b0101;
        break;
      default:
        array[3] = 0b0000;
    }
    return array;
  }

  private boolean isItemStackEnchanted(ItemStack itemStack) {
    return !itemStack.getEnchantments().isEmpty();
  }
}
