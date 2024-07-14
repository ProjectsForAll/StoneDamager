package host.plas.stonedamager.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import tv.quaint.events.components.BaseEvent;

@Setter
@Getter
public class EntityStepOnDamagerEvent extends BaseEvent {
    private LivingEntity entity;
    private boolean fireImmediately;

    public EntityStepOnDamagerEvent(LivingEntity entity, boolean fireImmediately) {
        this.entity = entity;
        this.fireImmediately = fireImmediately;
    }

    public EntityStepOnDamagerEvent(LivingEntity entity) {
        this(entity, true);
    }
}
