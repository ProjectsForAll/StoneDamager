package host.plas.stonedamager.runnables;

import host.plas.stonedamager.DamageHandler;
import host.plas.stonedamager.StoneDamager;
import io.streamlined.bukkit.instances.BaseRunnable;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

@Getter @Setter
public class TickTicker extends BaseRunnable {
    public TickTicker() {
        super(0, 1, true);
    }

    @Override
    public void execute() {
        for (World world : StoneDamager.getInstance().getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                Bukkit.getScheduler().runTask(StoneDamager.getInstance(), () -> {
                    DamageHandler.checkEntity(entity);
                });
            }
        }
    }
}
