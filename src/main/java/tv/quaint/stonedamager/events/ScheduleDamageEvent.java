package tv.quaint.stonedamager.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import tv.quaint.events.components.BaseEvent;

public class ScheduleDamageEvent extends BaseEvent {
    @Getter @Setter
    LivingEntity e;

    public ScheduleDamageEvent(LivingEntity e) {
        this.e = e;
    }
}
