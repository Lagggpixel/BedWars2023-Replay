package me.lagggpixel.replay.commands;

import me.lagggpixel.replay.Main;
import me.lagggpixel.replay.support.nms.data.Cache;
import me.lagggpixel.replay.support.nms.data.IReplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Kiiya
 * @since April 28, 2024
 */

public class ReplayCommand implements CommandExecutor {
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if (strings.length == 0) {
      commandSender.sendMessage("Usage: /replay <start/stop/play>");
      return false;
    }
    Player player = (Player)commandSender;
    if (strings[0].equalsIgnoreCase("start")) {
      IReplay replay = Main.getVersionAdapter().createNewReplay(player);
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
      commandSender.sendMessage("Usage: /replay <start/stop/play>");
    }
    return true;
  }
}

