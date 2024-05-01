package me.lagggpixel.replay.api.recordable.entity.player.recordables;

import me.lagggpixel.replay.api.recordable.entity.player.IPlayerRecordable;
import org.bukkit.inventory.ItemStack;

public interface IEquipment extends IPlayerRecordable {

  ItemStack getMainHand();

  ItemStack getOffhand();

  ItemStack getHelmet();

  ItemStack getChestplate();

  ItemStack getLeggings();

  ItemStack getBoots();
}
