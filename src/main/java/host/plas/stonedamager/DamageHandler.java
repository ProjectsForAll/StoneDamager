package host.plas.stonedamager;

import host.plas.stonedamager.events.EntityStepOnDamagerEvent;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class DamageHandler {
    public static void checkEntity(Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) entity;
            if (e.getLocation().getBlock().getType() != Material.STONECUTTER) return;

            if (StoneDamager.getDamagerConfig().isWorldWhitelist()) {
                if (StoneDamager.getDamagerConfig().getWorlds().contains(e.getWorld().getName())) {
                    checkEntityAndRun(e);
                }
            } else {
                if (! StoneDamager.getDamagerConfig().getWorlds().contains(e.getWorld().getName())) {
                    checkEntityAndRun(e);
                }
            }
        }
    }

    private static void checkEntityAndRun(LivingEntity e) {
        if (StoneDamager.getDamagerConfig().isEntityWhitelist()) {
            if (StoneDamager.getDamagerConfig().getEntityTypes().contains(e.getType().name())) {
                new EntityStepOnDamagerEvent(e).fire();
            }
        } else {
            if (! StoneDamager.getDamagerConfig().getEntityTypes().contains(e.getType().name())) {
                new EntityStepOnDamagerEvent(e).fire();
            }
        }
    }
}
