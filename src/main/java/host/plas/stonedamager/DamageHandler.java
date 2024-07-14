package host.plas.stonedamager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

public class DamageHandler {
    public static boolean isOnDamager(Entity entity) {
        Location location = entity.getLocation();
        return location.getBlock().getType() == Material.STONECUTTER;
    }

    public static boolean checkWorldThenEntity(Entity entity) {
        if (! isOnDamager(entity)) return false;

        if (StoneDamager.getDamagerConfig().isWorldWhitelist()) {
            if (StoneDamager.getDamagerConfig().getWorlds().contains(entity.getWorld().getName())) {
                return checkEntity(entity);
            }
        } else {
            if (! StoneDamager.getDamagerConfig().getWorlds().contains(entity.getWorld().getName())) {
                return checkEntity(entity);
            }
        }

        return false;
    }

    public static boolean checkEntity(Entity entity) {
        if (StoneDamager.getDamagerConfig().isEntityWhitelist()) {
            return StoneDamager.getDamagerConfig().getEntityTypes().contains(entity.getType().name());
        } else {
            return ! StoneDamager.getDamagerConfig().getEntityTypes().contains(entity.getType().name());
        }
    }
}
