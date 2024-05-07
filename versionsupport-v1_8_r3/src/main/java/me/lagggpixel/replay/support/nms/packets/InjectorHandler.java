package me.lagggpixel.replay.support.nms.packets;

import io.netty.channel.Channel;
import me.lagggpixel.replay.support.nms.v1_8_R3;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling injections.
 *
 * @author Kiiya
 * @since May 07, 2024
 */
public class InjectorHandler implements Listener {
  // List of players that are injected.
  private final List<Player> injectedPlayers;
  // List of injections.
  private final List<Injector> injections;

  // Singleton instance.
  private static InjectorHandler instance;

  // Private constructor.
  private InjectorHandler() {
    this.injectedPlayers = new ArrayList<>();
    this.injections = new ArrayList<>();
  }

  // Initialize the singleton instance.
  public static void init() {
    if (instance != null) return;
    instance = new InjectorHandler();
    for (Player p : v1_8_R3.getInstance().getServer().getOnlinePlayers()) {
      instance.inject(p);
    }
    v1_8_R3.getInstance().getServer().getPluginManager().registerEvents(instance, v1_8_R3.getInstance().getPlugin());
    v1_8_R3.getInstance().getServer().getPluginManager().registerEvents(new PacketListener(), v1_8_R3.getInstance().getPlugin());
    v1_8_R3.getInstance().getPlugin().getLogger().info("Packet handlers registered.");
  }

  // Get the singleton instance.
  public static InjectorHandler getInstance() {
    return instance;
  }

  // Get the list of injections.
  public List<Injector> getInjections() {
    return this.injections;
  }

  // Get the list of injected players.
  public List<Player> getInjectedPlayers() {
    return this.injectedPlayers;
  }

  // Inject a player.
  public void inject(Player p) {
    if (isInjected(p)) return;

    injections.add(new Injector(p));
    this.injectedPlayers.add(p);
  }

  // Uninject a player.
  public void uninject(Player p) {
    if (!isInjected(p)) return;

    this.injectedPlayers.remove(p);

    Channel channel = ((CraftPlayer)p).getHandle().playerConnection.networkManager.channel;
    channel.eventLoop().submit(() -> {
      channel.pipeline().remove(p.getName());
      return null;
    });
  }

  // Check if a player is injected.
  public boolean isInjected(Player p) {
    return this.injectedPlayers.contains(p);
  }

  // When a player joins, inject it.
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    this.inject(e.getPlayer());
  }

  // When a player quits, uninject it.
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    this.uninject(e.getPlayer());
  }
}