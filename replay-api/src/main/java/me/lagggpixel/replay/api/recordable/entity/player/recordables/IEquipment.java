package me.lagggpixel.replay.api.recordable.entity.player.recordables;

import org.bukkit.inventory.ItemStack;

public interface IEquipment {

  ItemStack getMainHand();

  ItemStack getOffhand();

  ItemStack getHelmet();

  ItemStack getChestplate();

  ItemStack getLeggings();

  ItemStack getBoots();
}
