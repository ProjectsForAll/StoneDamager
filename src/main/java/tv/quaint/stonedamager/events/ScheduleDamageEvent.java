package tv.quaint.stonedamager.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import tv.quaint.events.components.BaseEvent;

public class ScheduleDamageEvent extends BaseEvent {
    @Getter @Setter
    Mob mob;

    public ScheduleDamageEvent(Mob mob) {
        this.mob = mob;
    }
}
