package tv.quaint.stonedamager.runnables;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import tv.quaint.stonedamager.DamageHandler;
import tv.quaint.stonedamager.StoneDamager;
import tv.quaint.stonedamager.events.ScheduleDamageEvent;

public class DamageTicker implements Runnable {
    @Getter @Setter
    int currentTick;
    @Getter @Setter
    LivingEntity e;

    public DamageTicker(LivingEntity e, boolean fireImmediately) {
        this.e = e;
        this.currentTick = 0;

        if (fireImmediately) new ScheduleDamageEvent(e).fire();
    }

    public DamageTicker(LivingEntity e) {
        this(e, true);
    }

    @Override
    public void run() {
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
