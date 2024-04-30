package me.lagggpixel.support.version.data;

import me.lagggpixel.replay.nms.data.Cache;
import me.lagggpixel.replay.nms.data.IFrame;
import me.lagggpixel.replay.nms.data.IReplay;
import me.lagggpixel.support.version.V1_8_R3;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftSound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * @author Kiiya
 * @since April 28, 2024
 */
public class Replay implements IReplay {
  private final Player player;
  private final List<IFrame> frames;
  private int recordingTaskId;

  public Replay(Player player, List<IFrame> frames) {
    this.player = player;
    this.frames = frames;
  }

  @Override
  public List<IFrame> getFrames() {
    return frames;
  }

  @Override
  public void play(Player player) {
    EntityPlayer npc = V1_8_R3.getInstance().getUtility().createNpc(player, frames.get(0).getLocation(), this.player.getDisplayName());
    PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
    for (IFrame frame : frames) {
      int delay = frames.indexOf(frame);
      Bukkit.getScheduler().runTaskLaterAsynchronously(V1_8_R3.getPlugin(), () -> {
        Location location = frame.getLocation();
        npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        npc.setSneaking(frame.isSneaking());
        if (frame.isBlocking()) npc.getDataWatcher().watch(8, (byte) 0x01);
        else npc.getDataWatcher().watch(8, (byte) 0x00);
        PacketPlayOutEntityTeleport movementPacket = new PacketPlayOutEntityTeleport(npc);
        PacketPlayOutEntityHeadRotation headRotation = new PacketPlayOutEntityHeadRotation(npc, (byte) ((location.getYaw() * 256.0F) / 360.0F));
        PacketPlayOutEntity.PacketPlayOutEntityLook packetPlayOutEntityLook = new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) ((location.getYaw() * 256.0F) / 360.0F), (byte) ((location.getPitch() * 256.0F) / 360.0F), true);
        PacketPlayOutEntityEquipment handEquipmentPacket = new PacketPlayOutEntityEquipment(npc.getId(), 0, CraftItemStack.asNMSCopy(frame.getItemInHand() == null ? new ItemStack(Material.AIR) : frame.getItemInHand()));
        PacketPlayOutEntityEquipment helmetEquipmentPacket = new PacketPlayOutEntityEquipment(npc.getId(), 4, CraftItemStack.asNMSCopy(frame.getHelmet() == null ? new ItemStack(Material.AIR) : frame.getHelmet()));
        PacketPlayOutEntityEquipment chestplateEquipmentPacket = new PacketPlayOutEntityEquipment(npc.getId(), 3, CraftItemStack.asNMSCopy(frame.getChestplate() == null ? new ItemStack(Material.AIR) : frame.getChestplate()));
        PacketPlayOutEntityEquipment leggingsEquipmentPacket = new PacketPlayOutEntityEquipment(npc.getId(), 2, CraftItemStack.asNMSCopy(frame.getLeggings() == null ? new ItemStack(Material.AIR) : frame.getLeggings()));
        PacketPlayOutEntityEquipment bootsEquipmentPacket = new PacketPlayOutEntityEquipment(npc.getId(), 1, CraftItemStack.asNMSCopy(frame.getBoots() == null ? new ItemStack(Material.AIR) : frame.getBoots()));
        PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(npc.getId(), npc.getDataWatcher(), true);
        PacketPlayOutNamedSoundEffect soundPacket = new PacketPlayOutNamedSoundEffect(getSounds(location.getBlock()).getStepSound(), location.getX(), location.getY(), location.getZ(), getSounds(location.getBlock()).getVolume2(), getSounds(location.getBlock()).getVolume1());

        if (frame.isHitting()) {
          PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(npc, 0);
          connection.sendPacket(animationPacket);
          if (frame.getBlockLocation() != null) {
            Block block = location.getWorld().getBlockAt(frame.getBlockLocation());
            Bukkit.getScheduler().runTask(V1_8_R3.getPlugin(), () -> {
              block.setType(Material.AIR);
            });
            Block finalBlock = location.getWorld().getBlockAt(frame.getBlockLocation());
            BlockPosition blockPosition = new BlockPosition(frame.getBlockLocation().getBlockX(), frame.getBlockLocation().getBlockY(), frame.getBlockLocation().getBlockZ());
            WorldServer world = ((CraftWorld) frame.getBlockLocation().getWorld()).getHandle();
            for (ItemStack drop : block.getDrops()) {
              EntityItem entityItem = new EntityItem(world, blockPosition.getX(), blockPosition.getY(), blockPosition.getZ(), CraftItemStack.asNMSCopy(drop));
              CraftItem craftItem = new CraftItem(((CraftServer) V1_8_R3.getPlugin().getServer()), entityItem);
              craftItem.setPickupDelay(0);
              craftItem.setVelocity(new Vector(0.1, 0.2, 0.1));
              PacketPlayOutSpawnEntity spawnEntity = new PacketPlayOutSpawnEntity(entityItem, 2);
              PacketPlayOutEntityMetadata entityMetadata = new PacketPlayOutEntityMetadata(entityItem.getId(), entityItem.getDataWatcher(), true);
              PacketPlayOutEntityVelocity velocityPacket = new PacketPlayOutEntityVelocity(entityItem);
              connection.sendPacket(spawnEntity);
              connection.sendPacket(entityMetadata);
              connection.sendPacket(velocityPacket);
            }
            PacketPlayOutNamedSoundEffect breakSoundPacket = new PacketPlayOutNamedSoundEffect(getSounds(finalBlock).getBreakSound(), location.getX(), location.getY(), location.getZ(), getSounds(finalBlock).getVolume2(), getSounds(finalBlock).getVolume1());
            PacketPlayOutBlockChange blockChangePacket = new PacketPlayOutBlockChange(world, blockPosition);
            connection.sendPacket(blockChangePacket);
            connection.sendPacket(breakSoundPacket);
          }
        }

        if (frame.isPlacing() && frame.getBlockLocation() != null && frame.getItemInHand() != null && frame.getItemInHand().getType().isBlock()) {
          Block block = location.getWorld().getBlockAt(frame.getBlockLocation());
          Bukkit.getScheduler().runTask(V1_8_R3.getPlugin(), () -> {
            block.setType(frame.getItemInHand().getType());
            block.setData(frame.getItemInHand().getData().getData());
          });
          Block finalBlock = location.getWorld().getBlockAt(frame.getBlockLocation());
          PacketPlayOutNamedSoundEffect placeSoundPacket = new PacketPlayOutNamedSoundEffect(getSounds(finalBlock).getBreakSound(), location.getX(), location.getY(), location.getZ(), getSounds(finalBlock).getVolume2(), getSounds(finalBlock).getVolume1());
          PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(npc, 0);
          PacketPlayOutBlockChange blockChangePacket = new PacketPlayOutBlockChange(((CraftWorld) frame.getBlockLocation().getWorld()).getHandle(), new BlockPosition(frame.getBlockLocation().getBlockX(), frame.getBlockLocation().getBlockY(), frame.getBlockLocation().getBlockZ()));
          connection.sendPacket(animationPacket);
          connection.sendPacket(blockChangePacket);
          connection.sendPacket(placeSoundPacket);
        }

        if (frame.isDamaged()) {
          PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(npc, 1);
          PacketPlayOutNamedSoundEffect damageSoundPacket = new PacketPlayOutNamedSoundEffect(CraftSound.getSound(Sound.HURT_FLESH), location.getX(), location.getY(), location.getZ(), 1.0F, 1.0F);
          connection.sendPacket(animationPacket);
          connection.sendPacket(damageSoundPacket);
        }

        if (frame.getPickupItem() != null) {
          PacketPlayOutCollect collectPacket = new PacketPlayOutCollect(frame.getPickupItem().getEntityId(), npc.getId());
          PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(frame.getPickupItem().getEntityId());
          connection.sendPacket(collectPacket);
          connection.sendPacket(destroyPacket);
        }
        if (frame.getDropItem() != null) {
          Entity drop = ((CraftItem) frame.getDropItem()).getHandle();
          PacketPlayOutSpawnEntity spawnEntity = new PacketPlayOutSpawnEntity(drop, 2);
          PacketPlayOutEntityMetadata entityMetadata = new PacketPlayOutEntityMetadata(drop.getId(), drop.getDataWatcher(), true);
          Vector dropDirection = new Location(npc.getWorld().getWorld(), npc.locX, npc.locY + 1.8, npc.locZ, npc.yaw, npc.pitch).getDirection();
          PacketPlayOutEntityVelocity velocityPacket = new PacketPlayOutEntityVelocity(drop.getId(), dropDirection.getX(), dropDirection.getY(), dropDirection.getZ());
          connection.sendPacket(spawnEntity);
          connection.sendPacket(entityMetadata);
          connection.sendPacket(velocityPacket);
        }
        connection.sendPacket(metadataPacket);
        if (frames.get(frames.indexOf(frame) == 0 ? 0 : frames.indexOf(frame) - 1).getLocation().distance(location) > 1 && frame.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
          connection.sendPacket(soundPacket);
        }
        connection.sendPacket(movementPacket);
        connection.sendPacket(headRotation);
        connection.sendPacket(packetPlayOutEntityLook);
        connection.sendPacket(handEquipmentPacket);
        connection.sendPacket(helmetEquipmentPacket);
        connection.sendPacket(chestplateEquipmentPacket);
        connection.sendPacket(leggingsEquipmentPacket);
        connection.sendPacket(bootsEquipmentPacket);

        int currentIndex = frames.indexOf(frame);
        int finalIndex = frames.size() - 1;
        if (currentIndex == finalIndex) {
          Bukkit.getScheduler().runTaskLater(V1_8_R3.getPlugin(), () -> {
            PacketPlayOutPlayerInfo removePlayerPacket = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc);
            PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(npc.getId());
            connection.sendPacket(removePlayerPacket);
            connection.sendPacket(destroyPacket);
            player.sendMessage(ChatColor.GREEN + "Replay finished!");
          }, 20);
        }
      }, delay);
    }
  }

  @Override
  public void startRecording() {
    Cache.replays.put(player, this);
    Cache.recordingPlayers.add(player);
    recordingTaskId = Bukkit.getScheduler().runTaskTimerAsynchronously(V1_8_R3.getPlugin(), () -> {
      frames.add(new IFrame(player));
    }, 0, 1).getTaskId();
  }

  @Override
  public void stopRecording() {
    Cache.recordingPlayers.remove(player);
    Bukkit.getScheduler().cancelTask(recordingTaskId);
  }

  public static net.minecraft.server.v1_8_R3.Block.StepSound getSounds(Block block) {
    net.minecraft.server.v1_8_R3.Block nmsBlock = CraftMagicNumbers.getBlock(block);
    return nmsBlock.stepSound;
  }
}