package host.plas.stonedamager;

import io.streamlined.bukkit.PluginBase;
import lombok.Getter;
import lombok.Setter;
import host.plas.stonedamager.config.DamagerConfig;
import host.plas.stonedamager.events.DamageListener;
import host.plas.stonedamager.runnables.TickTicker;

@Getter @Setter
public final class StoneDamager extends PluginBase {
    @Getter @Setter
    private static StoneDamager instance;
    @Getter @Setter
    private static DamagerConfig damagerConfig;
    @Getter @Setter
    private static DamageListener damageListener;

    @Getter @Setter
    private static TickTicker tickTicker;

    @Override
    public void onBaseEnabled() {
        // Plugin startup logic
        instance = this;

        damagerConfig = new DamagerConfig();

        tickTicker = new TickTicker();

        damageListener = new DamageListener();
    }

    @Override
    public void onBaseDisable() {
        // Plugin shutdown logic
    }
}
