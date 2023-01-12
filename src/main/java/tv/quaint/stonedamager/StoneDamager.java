package tv.quaint.stonedamager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tv.quaint.objects.handling.derived.PluginEventable;
import tv.quaint.stonedamager.config.DamagerConfig;
import tv.quaint.stonedamager.events.DamageListener;
import tv.quaint.stonedamager.runnables.TickTicker;

public final class StoneDamager extends JavaPlugin {
    @Getter
    static StoneDamager instance;
    @Getter
    static DamagerConfig damagerConfig;
    @Getter
    static DamageListener damageListener;
    @Getter
    static MyEventable eventable;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        damagerConfig = new DamagerConfig();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TickTicker(), 0, 1);

        eventable = new MyEventable(getName());

        damageListener = new DamageListener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public class MyEventable extends PluginEventable {
        public MyEventable(String identifier) {
            super(identifier, false);
        }
    }
}
