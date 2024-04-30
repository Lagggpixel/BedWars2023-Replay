package me.lagggpixel.replay.version_support.nms.data;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lagggpixel
 * @since April 28, 2024
 */
public class Cache {

  public static final HashMap<Player, List<Block>> blocks = new HashMap<>();

  public static final HashMap<Player, List<Block>> originalBlocks = new HashMap<>();

  public static final HashMap<Player, IReplay> replays = new HashMap<>();

  public static final List<Player> recordingPlayers = new ArrayList<>();
}
