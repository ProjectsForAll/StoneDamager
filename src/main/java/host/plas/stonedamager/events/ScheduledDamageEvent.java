package host.plas.stonedamager.events;

import host.plas.stonedamager.data.DamagableSelection;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import tv.quaint.events.components.BaseEvent;

@Setter
@Getter
public class ScheduledDamageEvent extends BaseEvent {
    private LivingEntity entity;
    private DamagableSelection damagableSelection;

    public ScheduledDamageEvent(LivingEntity entity, DamagableSelection damagableSelection) {
        this.entity = entity;
        this.damagableSelection = damagableSelection;
    }
}
