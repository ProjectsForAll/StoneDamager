package tv.quaint.stonedamager.config;

import tv.quaint.stonedamager.StoneDamager;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.io.File;
import java.util.List;

public class DamagerConfig extends SimpleConfiguration {
    public DamagerConfig() {
        super("config.yml", StoneDamager.getInstance().getDataFolder(), true);
    }

    @Override
    public void init() {
        getResource().set("ticks-per-damage", 20);
        getResource().set("damage-amount", 3d);
        getResource().set("entities.types", List.of("ARMOR_STAND", "ITEM_FRAME"));
        getResource().set("entities.is-whitelist", false);
        getResource().set("worlds.name", List.of("world_nether", "world_the_end"));
        getResource().set("worlds.is-whitelist", false);
    }

    public int getTicksPerDamage() {
        reloadResource();

        return getResource().getInt("ticks-per-damage");
    }

    public double getDamageAmount() {
        reloadResource();

        return getResource().getDouble("damage-amount");
    }

    public List<String> getEntityTypes() {
        reloadResource();

        return getResource().getStringList("entities.types");
    }

    public boolean isEntityWhitelist() {
        reloadResource();

        return getResource().getBoolean("entities.is-whitelist");
    }

    public List<String> getWorlds() {
        reloadResource();

        return getResource().getStringList("worlds.names");
    }

    public boolean isWorldWhitelist() {
        reloadResource();

        return getResource().getBoolean("worlds.is-whitelist");
    }
}
