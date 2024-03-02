package host.plas.stonedamager.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import tv.quaint.events.components.BaseEvent;

@Setter
@Getter
public class ScheduleDamageEvent extends BaseEvent {
    LivingEntity e;

    public ScheduleDamageEvent(LivingEntity e) {
        this.e = e;
    }
}
