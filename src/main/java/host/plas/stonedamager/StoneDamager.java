package host.plas.stonedamager;

import host.plas.bou.BetterPlugin;
import host.plas.stonedamager.commands.ReloadCMD;
import host.plas.stonedamager.utils.DamageHandler;
import lombok.Getter;
import lombok.Setter;
import host.plas.stonedamager.config.DamagerConfig;
import host.plas.stonedamager.runnables.TickTicker;

@Getter @Setter
public final class StoneDamager extends BetterPlugin {
    @Getter @Setter
    private static StoneDamager instance;
    @Getter @Setter
    private static DamagerConfig damagerConfig;

    @Getter @Setter
    private static TickTicker tickTicker;

    public StoneDamager() {
        super();
    }

    @Override
    public void onBaseEnabled() {
        // Plugin startup logic
        instance = this;

        damagerConfig = new DamagerConfig();

        tickTicker = new TickTicker();

        new ReloadCMD();
    }

    @Override
    public void onBaseDisable() {
        // Plugin shutdown logic
        tickTicker.cancel();

        DamageHandler.clearTickables();
    }
}
