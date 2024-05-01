package me.lagggpixel.replay.api;

import me.lagggpixel.replay.api.data.player.IPlayerRecordable;

import java.util.UUID;

/**
 * @author Lagggpixel
 * @since May 01, 2024
 */
public interface IVersionSupport {

  IPlayerRecordable createPlayerRecordable(UUID uuid);
}
