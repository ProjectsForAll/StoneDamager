package tv.quaint.stonedamager.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import tv.quaint.events.components.BaseEvent;

public class EntityStepOnDamagerEvent extends BaseEvent {
    @Getter @Setter
    Mob mob;
    @Getter @Setter
    boolean fireImmediately;

    public EntityStepOnDamagerEvent(Mob mob, boolean fireImmediately) {
        this.mob = mob;
        this.fireImmediately = fireImmediately;
    }

    public EntityStepOnDamagerEvent(Mob mob) {
        this(mob, true);
    }
}
