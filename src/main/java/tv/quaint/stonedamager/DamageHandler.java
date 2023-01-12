package tv.quaint.stonedamager;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import tv.quaint.stonedamager.events.EntityStepOnDamagerEvent;

public class DamageHandler {
    public static void checkEntity(Entity entity) {
        if (entity instanceof Mob) {
            Mob mob = (Mob) entity;
            if (mob.getLocation().getBlock().getType() != Material.STONECUTTER) return;

            if (StoneDamager.getDamagerConfig().isWorldWhitelist()) {
                if (StoneDamager.getDamagerConfig().getWorlds().contains(mob.getWorld().getName())) {
                    checkEntityAndRun(mob);
                }
            } else {
                if (! StoneDamager.getDamagerConfig().getWorlds().contains(mob.getWorld().getName())) {
                    checkEntityAndRun(mob);
                }
            }
        }
    }

    private static void checkEntityAndRun(Mob mob) {
        if (StoneDamager.getDamagerConfig().isEntityWhitelist()) {
            if (StoneDamager.getDamagerConfig().getEntityTypes().contains(mob.getType().name())) {
                new EntityStepOnDamagerEvent(mob).fire();
            }
        } else {
            if (!StoneDamager.getDamagerConfig().getEntityTypes().contains(mob.getType().name())) {
                new EntityStepOnDamagerEvent(mob).fire();
            }
        }
    }
}
