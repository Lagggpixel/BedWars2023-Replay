package me.lagggpixel.replay.commands;

import me.lagggpixel.replay.nms.data.Cache;
import me.lagggpixel.replay.nms.data.IReplay;
import me.lagggpixel.support.version.data.Replay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * @author Kiiya
 * @since April 28, 2024
 */

public class RecordingCommand implements CommandExecutor {
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if (strings.length == 0) {
      commandSender.sendMessage("Usage: /recording <start/stop/play>");
      return false;
    }
    Player player = (Player)commandSender;
    if (strings[0].equalsIgnoreCase("start")) {
      Replay replay = new Replay(player, new ArrayList<>());
      replay.startRecording();
      player.sendMessage("Recording started!");
    } else if (strings[0].equalsIgnoreCase("stop")) {
      if (Cache.replays.containsKey(player)) {
        IReplay replay = Cache.replays.get(player);
        replay.stopRecording();
        player.sendMessage("Recording stopped!");
      }
    } else if (strings[0].equalsIgnoreCase("play")) {
      if (Cache.replays.containsKey(player)) {
        IReplay replay = Cache.replays.get(player);
        player.sendMessage("Playing recording!");
        replay.play(player);
      }
    } else {
      commandSender.sendMessage("Usage: /recording <start/stop/play>");
    }
    return true;
  }
}

