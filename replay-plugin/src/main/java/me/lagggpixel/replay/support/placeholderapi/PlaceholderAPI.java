package me.lagggpixel.replay.support.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.lagggpixel.replay.Replay;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPI extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "replay";
    }

    @Override
    public @NotNull String getAuthor() {
        return "lagggpixel";
    }

    @Override
    public @NotNull String getVersion() {
        return Replay.getInstance().getDescription().getVersion();
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String args) {
        return null;
    }
}
