package host.plas.stonedamager.events;

import gg.drak.thebase.events.components.BaseEvent;
import host.plas.stonedamager.StoneDamager;

public class StoneDamagerEvent extends BaseEvent {
    public StoneDamagerEvent() {
        super();
    }

    public StoneDamager getPlugin() {
        return StoneDamager.getInstance();
    }
}
