package me.lagggpixel.replay.version_support.v1_8_r3.utils;

import com.mojang.authlib.GameProfile;
import me.lagggpixel.replay.version_support.nms.utils.IUtility;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since April 28, 2024
 */
public class Utility implements IUtility {

  @Override
  public String c(String s) {
    return ChatColor.translateAlternateColorCodes('&', s);
  }

  @Override
  public void sendPacket(Player p, Object packet) {
    (((CraftPlayer) p).getHandle()).playerConnection.sendPacket((Packet<PacketListenerPlayOut>) packet);
  }

  @Override
  public void sendPlayersPacket(Object packet) {
    for (Player player : Bukkit.getOnlinePlayers())
      (((CraftPlayer) player).getHandle()).playerConnection.sendPacket((Packet<PacketListenerPlayOut>) packet);
  }

  @Override
  public EntityPlayer createNpc(Player p, Location loc, String name) {
    MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
    WorldServer world = ((CraftWorld) loc.getWorld()).getHandle();
    GameProfile profile = new GameProfile(UUID.randomUUID(), c("&a[NPC] " + name));
    EntityPlayer npc = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));
    npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
    return npc;
  }
}
