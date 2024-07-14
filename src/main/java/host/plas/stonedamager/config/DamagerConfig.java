package host.plas.stonedamager.config;

import host.plas.stonedamager.StoneDamager;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.util.ArrayList;
import java.util.List;

public class DamagerConfig extends SimpleConfiguration {
    public DamagerConfig() {
        super("config.yml", StoneDamager.getInstance(), true);
    }

    @Override
    public void init() {
        getTicksPerDamage();
        getDamageAmount();
        getEntityTypes();
        isEntityWhitelist();
        getWorlds();
        isWorldWhitelist();
    }

    public int getTicksPerDamage() {
        reloadResource();

        return getOrSetDefault("ticks-per-damage", 20);
    }

    public double getDamageAmount() {
        reloadResource();

        return getOrSetDefault("damage-amount", 3d);
    }

    public List<String> getEntityTypes() {
        reloadResource();

        return getOrSetDefault("entities.types", new ArrayList<>(getDefaultTypes()));
    }

    public static List<String> getDefaultTypes() {
        return List.of(
                "ARMOR_STAND",
                "ITEM_FRAME",
                "PAINTING",
                "VILLAGER",
                "WANDERING_TRADER",
                "WOLF",
                "OCELOT",
                "CAT",
                "PARROT",
                "LLAMA",
                "TRADER_LLAMA",
                "DONKEY",
                "MULE",
                "HORSE",
                "SKELETON_HORSE",
                "ZOMBIE_HORSE",
                "ALLAY",
                "MINECART",
                "MINECART_CHEST",
                "MINECART_FURNACE",
                "MINECART_TNT",
                "MINECART_HOPPER",
                "MINECART_MOB_SPAWNER",
                "MINECART_COMMAND",
                "OAK_BOAT",
                "SPRUCE_BOAT",
                "BIRCH_BOAT",
                "JUNGLE_BOAT",
                "ACACIA_BOAT",
                "DARK_OAK_BOAT",
                "MANGROVE_BOAT",
                "CHERRY_BOAT",
                "BEE",
                "PLAYER"
        );
    }

    public boolean isEntityWhitelist() {
        reloadResource();

        return getOrSetDefault("entities.is-whitelist", false);
    }

    public List<String> getWorlds() {
        reloadResource();

        return getOrSetDefault("worlds.names", List.of("lobby"));
    }

    public boolean isWorldWhitelist() {
        reloadResource();

        return getOrSetDefault("worlds.is-whitelist", false);
    }
}
