package tv.quaint.stonedamager.events;

import tv.quaint.events.BaseEventHandler;
import tv.quaint.events.BaseEventListener;
import tv.quaint.events.processing.BaseProcessor;
import tv.quaint.stonedamager.StoneDamager;
import tv.quaint.stonedamager.runnables.DamageTicker;

public class DamageListener implements BaseEventListener {
    public DamageListener() {
        BaseEventHandler.bake(this, StoneDamager.getEventable());
    }

    @BaseProcessor
    public void onDamage(ScheduleDamageEvent event) {
        double damage = StoneDamager.getDamagerConfig().getDamageAmount();

        event.getMob().damage(damage);
    }

    @BaseProcessor
    public void onStep(EntityStepOnDamagerEvent event) {
        new DamageTicker(event.getMob(), event.isFireImmediately());
    }
}
