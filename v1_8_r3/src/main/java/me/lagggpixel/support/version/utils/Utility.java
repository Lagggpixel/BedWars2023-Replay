package me.lagggpixel.support.version.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.lagggpixel.replay.nms.utils.IUtility;
import me.lagggpixel.support.version.V1_8_R3;
import me.lagggpixel.support.version.tasks.CraftArmorRotate;
import me.lagggpixel.support.version.tasks.VehicleFloatTask;
import net.minecraft.server.v1_8_R3.*;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Lagggpixel
 * @since April 28, 2024
 */
public class Utility implements IUtility {
  private static final HashMap<Player, EntityArmorStand> playerVehicle = new HashMap<>();

  private static final HashMap<EntityArmorStand, CraftArmorStand> craftArmorStand = new HashMap<>();

  public static String c(String s) {
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
  public void spawnArmorStand(Player p) {
    Location loc = p.getLocation();
    EntityArmorStand as = new EntityArmorStand(((CraftPlayer) p).getHandle().getWorld(), loc.getX(), loc.getY(), loc.getZ());
    CraftArmorStand a = new CraftArmorStand((CraftServer) V1_8_R3.getPlugin().getServer(), as);
    a.setGravity(false);
    a.setBasePlate(false);
    a.setVisible(false);
    as.locX = loc.getX();
    as.locY = loc.getY();
    as.locZ = loc.getZ();
    PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(as);
    PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
    PacketPlayOutEntityEquipment equipmentPacket = new PacketPlayOutEntityEquipment(as.getId(), 4, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_BLOCK)));
    PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook teleportPacket = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(as.getId(), (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, false);
    sendPlayersPacket(spawnPacket);
    sendPlayersPacket(metadataPacket);
    sendPlayersPacket(equipmentPacket);
    sendPlayersPacket(teleportPacket);
    Bukkit.getScheduler().runTaskTimer(V1_8_R3.getPlugin(), new CraftArmorRotate(p, as, loc), 0L, 1L);
  }

  @Override
  public void spawnVehicle(Player p) {
    Location loc = p.getLocation();
    EntityArmorStand as = new EntityArmorStand(((CraftPlayer) p).getHandle().getWorld(), loc.getX(), loc.getY() - 1.0D, loc.getZ());
    CraftArmorStand a = new CraftArmorStand((CraftServer) V1_8_R3.getPlugin().getServer(), as);
    a.setGravity(false);
    a.setBasePlate(false);
    a.setVisible(false);
    a.setCustomName("kart");
    a.setCustomNameVisible(false);
    PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(as);
    PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
    PacketPlayOutEntityEquipment equipmentPacket = new PacketPlayOutEntityEquipment(as.getId(), 4, CraftItemStack.asNMSCopy(new ItemStack(Material.FURNACE)));
    sendPlayersPacket(spawnPacket);
    sendPlayersPacket(metadataPacket);
    sendPlayersPacket(equipmentPacket);
    playerVehicle.put(p, as);
    craftArmorStand.put(as, a);
    getOnVehicle(p);
    Bukkit.getScheduler().runTaskTimer(V1_8_R3.getPlugin(), new VehicleFloatTask(as), 0L, 1L);
  }

  @Override
  public void getOnVehicle(Player p) {
    EntityArmorStand as = playerVehicle.get(p);
    CraftArmorStand a = craftArmorStand.get(as);
    a.setPassenger(p);
  }

  @Override
  public EntityArmorStand getVehicle(Player p) {
    return playerVehicle.get(p);
  }

  @Override
  public CraftArmorStand getCraftArmorStand(Object as) {
    return craftArmorStand.get((EntityArmorStand) as);
  }

  @Override
  public ItemStack getSkull(String url) {
    Field profileField;
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
    if (url == null || url.isEmpty())
      return skull;
    ItemMeta skullMeta = skull.getItemMeta();
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    try {
      profileField = skullMeta.getClass().getDeclaredField("profile");
    } catch (NoSuchFieldException | SecurityException e) {
      throw new RuntimeException(e);
    }
    profileField.setAccessible(true);
    try {
      profileField.set(skullMeta, profile);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    skull.setItemMeta(skullMeta);
    return skull;
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
