package me.lagggpixel.replay.support.bedwars;

import org.bukkit.plugin.Plugin;

public class BedWars2023 {

    public BedWars2023() {
        load();
    }

    private void load() {

    }

    private static class Addon extends com.tomkeuper.bedwars.api.addon.Addon {

        @Override
        public String getAuthor() {
            return "";
        }

        @Override
        public Plugin getPlugin() {
            return null;
        }

        @Override
        public String getVersion() {
            return "";
        }

        @Override
        public String getName() {
            return "";
        }

        @Override
        public String getDescription() {
            return "";
        }

        @Override
        public void load() {

        }

        @Override
        public void unload() {

        }
    }
}
