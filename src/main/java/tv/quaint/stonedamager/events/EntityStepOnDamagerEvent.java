package tv.quaint.stonedamager.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import tv.quaint.events.components.BaseEvent;

public class EntityStepOnDamagerEvent extends BaseEvent {
    @Getter @Setter
    LivingEntity e;
    @Getter @Setter
    boolean fireImmediately;

    public EntityStepOnDamagerEvent(LivingEntity e, boolean fireImmediately) {
        this.e = e;
        this.fireImmediately = fireImmediately;
    }

    public EntityStepOnDamagerEvent(LivingEntity e) {
        this(e, true);
    }
}
