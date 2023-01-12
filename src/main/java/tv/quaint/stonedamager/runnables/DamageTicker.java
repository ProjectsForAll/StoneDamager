package tv.quaint.stonedamager.runnables;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Mob;
import tv.quaint.stonedamager.DamageHandler;
import tv.quaint.stonedamager.StoneDamager;
import tv.quaint.stonedamager.events.ScheduleDamageEvent;

public class DamageTicker implements Runnable {
    @Getter @Setter
    int currentTick;
    @Getter @Setter
    Mob mob;

    public DamageTicker(Mob mob, boolean fireImmediately) {
        this.mob = mob;
        this.currentTick = 0;

        if (fireImmediately) new ScheduleDamageEvent(mob).fire();
    }

    public DamageTicker(Mob mob) {
        this(mob, true);
    }

    @Override
    public void run() {
        currentTick ++;

        if (currentTick >= StoneDamager.getDamagerConfig().getTicksPerDamage()) {
            try {
                DamageHandler.checkEntity(mob);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentTick = 0;
        }
    }
}
