package host.plas.stonedamager.events;

import host.plas.stonedamager.StoneDamager;
import host.plas.stonedamager.data.DamagableSelection;
import host.plas.stonedamager.objects.DistanceComparator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Optional;

@Setter
@Getter
public class ScheduledDamageEvent extends StoneDamagerEvent {
    private LivingEntity entity;
    private DamagableSelection damagableSelection;

    public ScheduledDamageEvent(LivingEntity entity, DamagableSelection damagableSelection) {
        this.entity = entity;
        this.damagableSelection = damagableSelection;
    }

    public Optional<Player> getClosestPlayer() {
        boolean tryFind = StoneDamager.getMainConfig().isTryFindPlayer();
        if (! tryFind) return Optional.empty();

        double radius = StoneDamager.getMainConfig().getFindPlayerRadius();
        return entity.getNearbyEntities(radius, radius, radius).stream()
                .filter(e -> e instanceof Player)
                .map(e -> (Player) e)
                .min(new DistanceComparator(getEntityLocation()));
    }

    public Location getEntityLocation() {
        return entity.getLocation();
    }

    /**
     * Damages the entity by the specified amount.
     *
     * Call this method only in synchronous context.
     * @param damage the amount of damage to deal
     */
    public void damageEntity(double damage) {
        getClosestPlayer().ifPresentOrElse(
                p -> getEntity().damage(damage, p),
                () -> getEntity().damage(damage)
        );
    }

    /**
     * Damages the entity by the amount specified in the DamagableSelection.
     *
     * Call this method only in synchronous context.
     */
    public void damageEntity() {
        damageEntity(getDamagableSelection().getDamageAmount());
    }
}
