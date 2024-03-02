package host.plas.stonedamager.events;

import host.plas.stonedamager.StoneDamager;
import host.plas.stonedamager.runnables.DamageTicker;
import tv.quaint.events.BaseEventHandler;
import tv.quaint.events.BaseEventListener;
import tv.quaint.events.processing.BaseProcessor;

public class DamageListener implements BaseEventListener {
    public DamageListener() {
        BaseEventHandler.bake(this, StoneDamager.getInstance());
    }

    @BaseProcessor
    public void onDamage(ScheduleDamageEvent event) {
        double damage = StoneDamager.getDamagerConfig().getDamageAmount();

        event.getE().damage(damage);
    }

    @BaseProcessor
    public void onStep(EntityStepOnDamagerEvent event) {
        new DamageTicker(event.getE(), event.isFireImmediately());
    }
}
