package me.lagggpixel.replay.version_support.nms;

import me.lagggpixel.replay.version_support.nms.data.IFrame;
import me.lagggpixel.replay.version_support.nms.data.IReplay;
import me.lagggpixel.replay.version_support.nms.utils.IUtility;
import org.bukkit.entity.Player;

/**
 * @author Lagggpixel
 * @since April 28, 2024
 */
public interface NMS {

  IUtility getUtility();

  IReplay createNewReplay(Player player);

  IFrame createNewFrame(Player player);
}
