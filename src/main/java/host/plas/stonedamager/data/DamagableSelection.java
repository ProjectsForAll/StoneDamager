package host.plas.stonedamager.data;

import host.plas.bou.configs.bits.ConfigurableWhitelist;
import host.plas.stonedamager.patch.StoneCutterPatch;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import tv.quaint.objects.Identifiable;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter @Setter
public class DamagableSelection implements Identifiable {
    private String identifier;

    private ConfigurableWhitelist<String> materials, entities, worlds;

    private String includePermission, excludePermission;

    private long ticksPerDamage;
    private double damageAmount;
    private double xOffset, yOffset, zOffset;

    private boolean enabled;

    public DamagableSelection(String identifier) {
        this.identifier = identifier;
    }

    public boolean isMaterialsContainsSC() {
        AtomicBoolean contains = new AtomicBoolean(false);

        materials.getWhitelist().forEach(material -> {
            if (material.toLowerCase().contains("cutter")) contains.set(true);
        });

        return contains.get();
    }

    public boolean checkMaterial(String material) {
        return materials.check(material);
    }

    public boolean checkMaterial(Block block) {
        if (isMaterialsContainsSC()) {
            if (StoneCutterPatch.isStoneCutter(block)) return ! materials.isBlacklist();
        }

        return checkMaterial(block.getType().name());
    }

    public boolean checkWorld(String world) {
        return worlds.check(world);
    }

    public boolean checkEntity(String entity) {
        return entities.check(entity);
    }

    public boolean checkPermissions(Entity player) {
        if (! (player instanceof Player)) return true;

        if (includePermission.isBlank() || includePermission.isEmpty()) {
            if (excludePermission.isBlank() || excludePermission.isEmpty()) {
                return true;
            } else {
                return ! player.hasPermission(excludePermission);
            }
        } else {
            if (excludePermission.isBlank() || excludePermission.isEmpty()) {
                return player.hasPermission(includePermission);
            } else {
                return player.hasPermission(includePermission) && ! player.hasPermission(excludePermission);
            }
        }
    }

    public boolean checkAll(Entity entity, Block block) {
        return checkEntity(entity.getType().name()) && checkWorld(block.getWorld().getName()) && checkMaterial(block) && checkPermissions(entity);
    }

    public boolean check(Entity entity) {
        if (! enabled) return false;

        Location location = entity.getLocation().clone();
        Block block = location.add(xOffset, yOffset, zOffset).getBlock();

        return checkAll(entity, block);
    }
}
