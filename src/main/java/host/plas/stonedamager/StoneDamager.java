package host.plas.stonedamager;

import host.plas.bou.BetterPlugin;
import host.plas.stonedamager.commands.ReloadCMD;
import host.plas.stonedamager.utils.DamageHandler;
import lombok.Getter;
import lombok.Setter;
import host.plas.stonedamager.config.MainConfig;
import host.plas.stonedamager.runnables.TickTicker;

@Getter @Setter
public final class StoneDamager extends BetterPlugin {
    @Getter @Setter
    private static StoneDamager instance;
    @Getter @Setter
    private static MainConfig mainConfig;

    @Getter @Setter
    private static TickTicker tickTicker;

    public StoneDamager() {
        super();
    }

    @Override
    public void onBaseEnabled() {
        // Plugin startup logic
        instance = this;

        mainConfig = new MainConfig();

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
