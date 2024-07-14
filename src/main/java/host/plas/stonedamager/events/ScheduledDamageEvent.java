package host.plas.stonedamager.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import tv.quaint.events.components.BaseEvent;

@Setter
@Getter
public class ScheduledDamageEvent extends BaseEvent {
    private LivingEntity entity;

    public ScheduledDamageEvent(LivingEntity entity) {
        this.entity = entity;
    }
}
