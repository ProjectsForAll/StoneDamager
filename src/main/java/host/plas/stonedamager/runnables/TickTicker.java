package host.plas.stonedamager.runnables;

import host.plas.bou.scheduling.BaseRunnable;
import host.plas.stonedamager.StoneDamager;
import host.plas.stonedamager.utils.DamageHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TickTicker extends BaseRunnable {
    public TickTicker() {
        super(0, 1);

        StoneDamager.getInstance().logInfo("&cStep Checker &rstarted.");
    }

    @Override
    public void run() {
        DamageHandler.tick();
    }
}
