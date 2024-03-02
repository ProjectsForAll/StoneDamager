package host.plas.stonedamager.runnables;

import io.streamlined.bukkit.instances.BaseRunnable;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import host.plas.stonedamager.DamageHandler;
import host.plas.stonedamager.StoneDamager;
import host.plas.stonedamager.events.ScheduleDamageEvent;

@Setter
@Getter
public class DamageTicker extends BaseRunnable {
    int currentTick;
    LivingEntity e;

    public DamageTicker(LivingEntity e, boolean fireImmediately) {
        super(0, 1, true);
        this.e = e;
        this.currentTick = 0;

        if (fireImmediately) new ScheduleDamageEvent(e).fire();
    }

    public DamageTicker(LivingEntity e) {
        this(e, true);
    }

    @Override
    public void execute() {
        currentTick ++;

        if (currentTick >= StoneDamager.getDamagerConfig().getTicksPerDamage()) {
            try {
                DamageHandler.checkEntity(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentTick = 0;
        }
    }
}
